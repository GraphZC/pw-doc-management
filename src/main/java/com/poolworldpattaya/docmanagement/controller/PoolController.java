package com.poolworldpattaya.docmanagement.controller;

import com.poolworldpattaya.docmanagement.entity.Pool;
import com.poolworldpattaya.docmanagement.entity.ServiceDay;
import com.poolworldpattaya.docmanagement.repository.PoolRepository;
import com.poolworldpattaya.docmanagement.repository.ServiceDayRepository;
import com.poolworldpattaya.docmanagement.request.CreatePoolRequest;
import com.poolworldpattaya.docmanagement.service.PoolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/pool")
public class PoolController {

    @Autowired
    private PoolRepository poolRepository;
    @Autowired
    private ServiceDayRepository serviceDayRepository;

    @GetMapping("/{id}")
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity getOnePool(@PathVariable UUID id){
        Pool pool = poolRepository.findById(id).get();
        ServiceDay serviceDay = serviceDayRepository.findById(pool.getServiceId()).get();
        return ResponseEntity.ok(pool);
    }

    public ResponseEntity createCustomer(@RequestBody CreatePoolRequest pool){
        Pool newpool = new Pool();
        ServiceDay newServiceDay = new ServiceDay();

        //set Pool
        newpool.setCutomerId(pool.getCutomerId());
        newpool.setAddress(pool.getAddress());
        newpool.setPrice(pool.getPrice());
        newpool.setPoolType(pool.getPoolType());
        newpool.setChemicalInclude(pool.isChemicalInclude());
        newpool.setInService(pool.isInService());
        //set ServiceDay
        newServiceDay.setPoolid(newpool.getId());
        newServiceDay.setMonday(pool.isMonday());
        newServiceDay.setTuesday(pool.isTuesday());
        newServiceDay.setWednesday(pool.isWednesday());
        newServiceDay.setThursday(pool.isThursday());
        newServiceDay.setFriday(pool.isFriday());
    }

}
