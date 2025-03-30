package org.example.zhn.Mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.zhn.Dao.PostTable;
import org.example.zhn.Dao.imageUrl;

import java.util.List;

@Mapper
public interface imageUrlMapper {

    @Insert("insert into imageurl(postid,imageUrl,createTime) value (#{image.postId},#{image.url},now())")
    boolean insertPost(imageUrl imageurl);

    @Select("select * from imageurl where postId")
    List<imageUrl> selectPost();

    @Delete("delete from imageurl where postId = #{postId}")
    boolean deletePost(int postId);
}
