package org.example.zhn.Mapper;

import org.apache.ibatis.annotations.*;
import org.example.zhn.Dao.User;

import java.util.List;

@Mapper
public interface userMapper {


    @Delete("delete from user where email = #{email}")
    boolean delete(String email);
//    根据邮箱或者电话号码删除

    @Insert("insert into user(username, password, email, whether, manage) " +
            "value (#{user.name},#{user.password},#{user.email},1,0)")
    boolean insert(User user);
//    普通用户注册

    @Insert("insert into user(Invitation,whether) value (#{invitation},0)")
    boolean invitation(String invitation);
//    生成邀请账号

    @Update("update user set username = #{user.name},password = #{user.password},email = #{user.email},whether = 1 where Invitation=#{invitation}" )
    boolean update(User user,String invitation);
//    对邀请账号进行注册

    @Select("SELECT COUNT(*) > 0 FROM user WHERE invitation = #{invitation} AND whether = 0")
    boolean verifyInvitation(String invitation);
//    判断邀请码是否被使用了

    @Select("SELECT COUNT(*) > 0 FROM user WHERE invitation = #{invitation} ")
    boolean verifyInvi(String invitation);
//   判断验证码是否存在

    @Select("select password from user where email = #{email}")
    String findUserByEmail(String email);

//    @Select("select invitation from user")
//    List<String> verifyInvitations(String invitation);
////    获取验证码列表

    @Update("update user set password = #{password} where email = #{email} ")
    Boolean updatePassword(String email,String password);
//    重置密码

    @Update("update user set avatar_url = #{avatar} where email = #{email} ")
    boolean updateAvatar(String email, String avatar);
//    更换头像

    @Update("update user set title = #{title} where email = #{email}")
    boolean updateTitle(String title);
//    更换个人说明
}
