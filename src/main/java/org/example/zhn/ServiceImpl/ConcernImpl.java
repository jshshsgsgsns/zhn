package org.example.zhn.ServiceImpl;

import org.example.zhn.Dao.Concern;

public interface ConcernImpl {
    boolean insertConcern(Concern concern);
//    添加关注

    boolean deleteConcern(Concern concern);
//    取消关注

}
