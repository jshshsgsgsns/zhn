package org.example.zhn.ServiceImpl;

import org.example.zhn.Dao.User;

public interface userImpl {

    boolean regularUser_insert(User user);
//    普通用户注册

    boolean delete(String email);
//    删除用户

    boolean updateInvitation(User user, String invitation);
//    根据邀请码进行对潜在管理员账号注册

    boolean insertInvitation(String invitation);
//    生成邀请码，并生成邀请码未注册账号
}
