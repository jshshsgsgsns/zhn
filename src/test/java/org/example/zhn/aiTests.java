package org.example.zhn;


import org.example.zhn.Service.DeepSeekService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class aiTests {

    @Autowired
    private DeepSeekService deepSeekService;

    @Test
    public void contextLoads() throws IOException, DeepSeekService.DeepSeekApiException {
        System.out.println(deepSeekService.getDeepSeekResponse("普通人一个月跑多少步好"));

    }

}
