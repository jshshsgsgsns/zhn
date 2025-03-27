package org.example.zhn.Utils;

public class Response<T> {

    // 响应码，通常用于标识响应的状态
    private int code;

    // 响应消息，通常用于描述响应的详细信息
    private String message;

    // 泛型数据，具体类型由调用时指定
    private T data;

    // 构造方法
    public Response(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 默认构造方法（不推荐直接使用，但可以为兼容性考虑）
    public Response() {}

    // Getter 和 Setter 方法
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    // 工厂方法：返回成功的响应
    public static <T> Response<T> success(T data) {
        return new Response<>(200, "Success", data);
    }

    // 工厂方法：返回成功的响应（没有数据）
    public static <T> Response<T> success() {
        return new Response<>(200, "Success", null);
    }

    // 工厂方法：返回失败的响应（带错误消息）
    public static <T> Response<T> failure(String message) {
        return new Response<>(500, message, null);
    }

    // 工厂方法：返回自定义状态码和消息的失败响应
    public static <T> Response<T> failure(int code, String message) {
        return new Response<>(code, message, null);
    }

    // 工厂方法：返回自定义状态码、消息和数据的响应
    public static <T> Response<T> response(int code, String message, T data) {
        return new Response<>(code, message, data);
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

