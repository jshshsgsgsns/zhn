package org.example.zhn.ServiceImpl;

import org.example.zhn.Dao.User;

public interface UserImpl {

    boolean regularUser_insert(User user);
//    普通用户注册

    boolean delete(String email);
//    删除用户

    String createInvitation();
//    生成邀请码，向数据库注入

    boolean updateInvitation(User user, String invitation);
//    根据邀请码进行对潜在管理员账号注册

    boolean insertInvitation(String invitation);
//    生成邀请码未注册账号

    boolean verifyUser(String email, String password);
//    获取邀请码列表，用来查询邀请码是否存在

    boolean updateAvatar(String email, String avatar);
//    修改头像

    boolean updatePassword(String email, String password);
//    修改密码
    boolean updateTitle(String email, String title);
}
