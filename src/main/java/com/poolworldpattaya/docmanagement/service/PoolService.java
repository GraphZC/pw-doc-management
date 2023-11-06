package com.poolworldpattaya.docmanagement.service;

import com.poolworldpattaya.docmanagement.entity.Pool;
import com.poolworldpattaya.docmanagement.entity.ServiceDay;
import com.poolworldpattaya.docmanagement.repository.PoolRepository;
import com.poolworldpattaya.docmanagement.repository.ServiceDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PoolService {
    @Autowired
    private PoolRepository poolRepository;

    @Autowired
    private ServiceDayRepository serviceDayRepository;
    public List<Pool> getAllPool(){
        return poolRepository.findAll();
    }

    public Pool getPoolById(UUID id){
        return poolRepository.findById(id).get();
    }
    public Pool createPool(Pool pool) {
        poolRepository.save(pool);
        return pool;
    }

    public Pool deletePool(UUID id){
        Pool pool = poolRepository.findById(id).get();
        poolRepository.deleteById(id);
        return pool;
    }

    public Pool updatePool(Pool pool){
        UUID id = pool.getId();

        Pool record = poolRepository.findById(id).get();
//        record.setCutomerId(pool.getCutomerId());
        record.setAddress(pool.getAddress());
        record.setInService(pool.isInService());
        record.setChemicalInclude(pool.isChemicalInclude());
        record.setPrice(pool.getPrice());


        return record;
    }
}
