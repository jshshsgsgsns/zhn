package org.example.zhn.Mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.zhn.Dao.PostTable;

import java.util.List;

@Mapper
public interface postMapper {


    boolean insertPost(PostTable post);
//    创建帖子

    boolean updatePost(PostTable post);
//    根据userid来修改帖子信息

    List<PostTable> selectPost(int section,String pages,int userid);
//    分页查询，如果p为空 就为全局搜索,


    @Delete("delete from posttable where userid = #{userid}")
    boolean deletePost(int userid);
//    删除帖子



}
