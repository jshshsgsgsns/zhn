package org.example.zhn.Service;

import org.example.zhn.Dao.User;
import org.example.zhn.Mapper.userMapper;
import org.example.zhn.ServiceImpl.userImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class user implements userImpl {

    @Autowired
    private userMapper mapper;

    @Override
    public boolean regularUser_insert(User user) {
        return mapper.insert(user);
    }

    @Override
    public boolean delete(String email) {
        return mapper.delete(email);
    }

    @Override
    public boolean updateInvitation(User user, String invitation) {
        if(mapper.verifyInvitation(invitation)) {
            return mapper.update(user,invitation);
        }
//        验证邀请码是否存在并且没有被使用
        return false;
    }

    @Override
    public boolean insertInvitation(String invitation) {
        return mapper.invitation(invitation);
    }
}
