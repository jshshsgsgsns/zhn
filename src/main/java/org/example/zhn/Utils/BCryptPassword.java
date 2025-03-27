package org.example.zhn.Utils;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptPassword {

    public static String hashPassword(String plainPassword) {
        // 生成一个盐并加密密码
        String salt = BCrypt.gensalt(12); // 12是加密的复杂度，范围是4到31
        return BCrypt.hashpw(plainPassword, salt);
    }

    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        // 验证密码是否匹配
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }



}
