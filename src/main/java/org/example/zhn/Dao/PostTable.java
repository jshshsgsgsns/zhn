package org.example.zhn.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostTable {
    private int id;
    private String title;
    private int section;
    private int userid;
    private String label;
    private int view;
    private int likes;
    private String image;
    private String content;
    private String createTime;
    private int permissions;

}
