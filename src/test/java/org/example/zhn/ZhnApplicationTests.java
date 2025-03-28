package org.example.zhn;

import org.example.zhn.Dao.User;
import org.example.zhn.ServiceImpl.userImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ZhnApplicationTests {

    @Autowired
    private userImpl UserImpl;

    @Test
    void contextLoads() {

    }

    @Test
    void insertInvitation() {
    boolean test = UserImpl.insertInvitation("invi");
    System.out.println(test);
    }
//    测试经过邀请码生成邀请账号

    @Test
    void updateInvitation() {
        User u = new User();
        u.setEmail("19953948944");
        u.setPassword("19953948944");
        u.setName("王");
        boolean test = UserImpl.updateInvitation(u,"invi");
    }
    //对邀请账号进行注册

    @Test
    void regularUser_insert() {
        User u = new User();
        u.setEmail("test");
        u.setPassword("test");
        u.setName("1");
    }

    @Test
    void verifyUser(){
        boolean test = UserImpl.verifyUser("19953948944","19953948944");
        System.out.println(test);
    }

    @Test
    void createInvitation (){
        String test = UserImpl.createInvitation();
        System.out.println(test);
    }
//    生成邀请码


}
