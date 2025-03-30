package org.example.zhn.Service;

import org.example.zhn.Dao.Concern;
import org.example.zhn.Mapper.concernMapper;
import org.example.zhn.ServiceImpl.ConcernImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class concern implements ConcernImpl {

    @Autowired
    private concernMapper mapper;

    @Override
    public boolean insertConcern(Concern concern) {
        return mapper.insertConcern(concern);
    }

    @Override
    public boolean deleteConcern(Concern concern) {
        return mapper.deleteConcern(concern);
    }
}
