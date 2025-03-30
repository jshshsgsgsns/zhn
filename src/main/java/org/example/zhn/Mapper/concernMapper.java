package org.example.zhn.Mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.example.zhn.Dao.Concern;

@Mapper
public interface concernMapper {

    @Insert("insert into concern(user_id, section_id) value (#{concern.user_id},#{concern.section_id})")
    boolean insertConcern(Concern concern);
//    添加关注

    @Delete("delete from concern where user_id =#{concern.user_id} and section_id = #{concern.section_id}")
    boolean deleteConcern(Concern concern);
//    取消关注
}
