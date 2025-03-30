package org.example.zhn.Controller;


import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.example.zhn.Service.DeepSeekService;
import org.example.zhn.Utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/aiChat")
public class AIChatController {

    @Autowired
    private DeepSeekService deepSeek;

    @GetMapping("/test")
    public Response sendMessage(@RequestParam String message) throws IOException, DeepSeekService.DeepSeekApiException {
        log.info(message);
        return Response.success(deepSeek.getDeepSeekResponse(message));

    }

}
