package org.example.zhn;

import org.example.zhn.Dao.User;
import org.example.zhn.Mapper.userMapper;
import org.example.zhn.ServiceImpl.UserImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ZhnApplicationTests {

    @Autowired
    private UserImpl userImpl;

    @Autowired
    private userMapper mapper;

    @Test
    void contextLoads() {

    }

    @Test
    void insertInvitation() {
    boolean test = userImpl.insertInvitation("invi");
    System.out.println(test);
    }
//    测试经过邀请码生成邀请账号


    @Test
    void verifyUser(){
        boolean test = userImpl.verifyUser("19953948944","19953948944");
        System.out.println(test);
    }

    @Test
    void createInvitation (){
        String test = userImpl.createInvitation();
        System.out.println(test);
    }
//    生成邀请码


    @Test
    void updateWang(){
        User user = new User();
        user.setEmail("19953948944");
        user.setTitle("迎着阳光盛大逃亡");
        mapper.updateUser(user);
        System.out.println(user.getId());
    }

}
