package org.example.zhn;

import org.example.zhn.Dao.PostTable;
import org.example.zhn.Mapper.postMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class postTests {

    @Autowired
    private postMapper mapper;

    @Test
    void insertPost(){
        PostTable postTable = new PostTable();
        postTable.setContent("123");
        postTable.setTitle("title");
        postTable.setUserid(1);
        mapper.insertPost(postTable);
        System.out.println(postTable);
    }

    @Test
    void selectPost(){
        System.out.println(mapper.selectPost(0,null,1));
    }

    @Test
    void updatePost(){}

    @Test
    void deletePost(){
        System.out.println(mapper.deletePost(1));
    }

    @Test
    void likes(){
        for(int i=0;i<10;i++){
            System.out.println(mapper.likesPost(1));
        }
    }

}
