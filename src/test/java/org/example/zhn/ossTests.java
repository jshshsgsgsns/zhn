package org.example.zhn;

import org.example.zhn.Utils.Oss;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@SpringBootTest
public class ossTests {
    @Test
    public void upTest() {

    File file = new File("C:\\Users\\jiang\\IdeaProjects\\zhn\\zy\\首页.png");
    System.out.println(Oss.upFile(file));

    }

}
