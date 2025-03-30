package org.example.zhn;

import org.example.zhn.ServiceImpl.ConcernImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class concernTests {

    @Autowired
    private ConcernImpl concernImpl;

    @Test
    void insertConcern() {}
}
