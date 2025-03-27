package org.example.zhn.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
@AllArgsConstructor
public class comment {
    private int id;
    private int post_id;
    private int user_id;
    private String content;
    private int parent_id;

}
