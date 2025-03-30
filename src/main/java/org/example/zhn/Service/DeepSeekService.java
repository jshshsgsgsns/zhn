package org.example.zhn.Service;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

@Service
public class DeepSeekService {
    private static final Logger logger = LoggerFactory.getLogger(DeepSeekService.class);
    private static final String API_URL = "https://api.deepseek.com/chat/completions";
    private static final int MAX_RETRIES = 3;
    private static final long INITIAL_RETRY_DELAY_MS = 1000;

    private final OkHttpClient client;
    private final ObjectMapper objectMapper;
    private final String apiKey;

    // 显式构造函数
    public DeepSeekService(
            @Value("${deepseek.api.key}") String apiKey,
            @Value("${deepseek.timeout.connect:10}") int connectTimeout,
            @Value("${deepseek.timeout.read:30}") int readTimeout,
            @Value("${deepseek.timeout.write:10}") int writeTimeout) {

        this.apiKey = apiKey;
        this.objectMapper = new ObjectMapper();
        this.client = new OkHttpClient.Builder()
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                .addInterceptor(new LoggingInterceptor())
                .build();
    }

    // 显式声明主业务方法
    public String getDeepSeekResponse(String query) throws DeepSeekApiException {
        logger.debug("Preparing request for query: {}", query);
        Request request = buildRequest(query);

        int retryCount = 0;
        IOException lastException = null;

        while (retryCount < MAX_RETRIES) {
            try {
                long startTime = System.currentTimeMillis();
                String response = executeRequest(request);
                long duration = System.currentTimeMillis() - startTime;

                logger.info("API request completed in {} ms", duration);
                return response;
            } catch (IOException e) {
                lastException = e;
                retryCount++;

                if (shouldRetry(e) && retryCount < MAX_RETRIES) {
                    logger.warn("API request failed (attempt {} of {}), retrying...",
                            retryCount, MAX_RETRIES, e);
                    waitForRetry(retryCount);
                } else {
                    break;
                }
            }
        }

        logger.error("API request failed after {} attempts", MAX_RETRIES, lastException);
        throw new DeepSeekApiException("Failed after " + MAX_RETRIES + " attempts", lastException);
    }

    // 显式声明请求构建方法
    private Request buildRequest(String query) {
        String requestBody = createRequestBody(query);
        return new Request.Builder()
                .url(API_URL)
                .post(RequestBody.create(requestBody, MediaType.parse("application/json")))
                .addHeader("Authorization", "Bearer " + apiKey)
                .addHeader("Content-Type", "application/json")
                .build();
    }

    // 显式声明请求体创建方法
    private String createRequestBody(String query) {
        return String.format("""
        {
            "model": "deepseek-chat",
            "messages": [
                {"role": "user", "content": "%s"}
            ],
            "temperature": 0.7
        }
        """, escapeJson(query));
    }

    // 显式声明请求执行方法
    private String executeRequest(Request request) throws IOException {
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("API request failed with code: " + response.code());
            }
            return parseResponse(response.body().string());
        }
    }

    // 显式声明响应解析方法
    private String parseResponse(String responseBody) throws IOException {
        JsonNode jsonNode = objectMapper.readTree(responseBody);

        if (jsonNode.has("choices") && jsonNode.get("choices").isArray()) {
            JsonNode firstChoice = jsonNode.get("choices").get(0);
            if (firstChoice.has("message") && firstChoice.get("message").has("content")) {
                return firstChoice.get("message").get("content").asText();
            }
        }
        throw new IOException("Unexpected API response format");
    }

    // 显式声明重试判断方法
    private boolean shouldRetry(IOException e) {
        return e instanceof SocketTimeoutException
                || e instanceof ConnectException
                || e.getCause() instanceof SocketTimeoutException;
    }

    // 显式声明重试等待方法
    private void waitForRetry(int retryCount) {
        try {
            long delay = INITIAL_RETRY_DELAY_MS * retryCount;
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // 显式声明JSON转义方法
    private String escapeJson(String input) {
        return input.replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
    }

    // 显式声明拦截器类
    private static class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long startTime = System.nanoTime();
            logger.debug("Sending request to {} {}", request.url().host(), request.url().encodedPath());

            try {
                Response response = chain.proceed(request);
                long durationMs = (System.nanoTime() - startTime) / 1_000_000;
                logger.debug("Received response for {} in {} ms with status {}",
                        request.url().encodedPath(), durationMs, response.code());
                return response;
            } catch (Exception e) {
                long durationMs = (System.nanoTime() - startTime) / 1_000_000;
                logger.error("Request to {} failed after {} ms",
                        request.url().encodedPath(), durationMs, e);
                throw e;
            }
        }
    }

    // 显式声明自定义异常类
    public static final class DeepSeekApiException extends Exception {
        public DeepSeekApiException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}