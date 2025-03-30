package org.example.zhn.Dao;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String avatar_url;
    private String Invitation;
    private String whether;
    private String title;
    private String manage;

}
