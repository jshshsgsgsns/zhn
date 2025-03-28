package org.example.zhn.Service;

import org.example.zhn.Dao.User;
import org.example.zhn.Mapper.userMapper;
import org.example.zhn.ServiceImpl.userImpl;
import org.example.zhn.Utils.BCryptPassword;
import org.example.zhn.Utils.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class user implements userImpl {

    @Autowired
    private userMapper mapper;

    @Override
    public boolean regularUser_insert(User user) {
        user.setPassword(BCryptPassword.hashPassword(user.getPassword()));
        return mapper.insert(user);
    }

    @Override
    public boolean delete(String email) {
        return mapper.delete(email);
    }

    @Override
    public boolean updateInvitation(User user, String invitation) {
        if(mapper.verifyInvitation(invitation)) {
            user.setPassword(BCryptPassword.hashPassword(user.getPassword()));
            return mapper.update(user,invitation);
        }
//        验证邀请码是否存在并且没有被使用
        return false;
    }


    @Override
    public boolean insertInvitation(String invitation) {
        return mapper.invitation(invitation);
    }

    @Override
    public boolean verifyUser(String email, String password) {
        return BCryptPassword.checkPassword(password,mapper.findUserByEmail(email));
    }

    @Override
    public String createInvitation() {
        String invitation = RandomStringGenerator.generateRandomString();
        while(mapper.verifyInvi(invitation)){
            invitation = RandomStringGenerator.generateRandomString();
        }
        return invitation;
    }


}
