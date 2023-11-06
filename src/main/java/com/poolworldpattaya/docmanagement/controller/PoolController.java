package com.poolworldpattaya.docmanagement.controller;

import com.poolworldpattaya.docmanagement.entity.Customer;
import com.poolworldpattaya.docmanagement.entity.Pool;
import com.poolworldpattaya.docmanagement.entity.ServiceDay;
import com.poolworldpattaya.docmanagement.enums.PoolType;
import com.poolworldpattaya.docmanagement.repository.PoolRepository;
import com.poolworldpattaya.docmanagement.repository.ServiceDayRepository;
import com.poolworldpattaya.docmanagement.request.CreatePoolRequest;
import com.poolworldpattaya.docmanagement.service.CustomerService;
import com.poolworldpattaya.docmanagement.service.PoolService;
import com.poolworldpattaya.docmanagement.service.ServiceDayService;
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
    private ServiceDayService serviceDayService;

    @Autowired
    private PoolService poolService;

    @Autowired
    private CustomerService customerService;

    @PostMapping("/")
//    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity<Pool> createPool(@RequestBody CreatePoolRequest request) {
        ServiceDay serviceDay = new ServiceDay();
        serviceDay.setMonday(request.isMonday());
        serviceDay.setTuesday(request.isTuesday());
        serviceDay.setWednesday(request.isWednesday());
        serviceDay.setThursday(request.isThursday());
        serviceDay.setFriday(request.isFriday());
        serviceDay.setSaturday(request.isSaturday());
        serviceDay.setSunday(request.isSunday());

        serviceDayService.createServiceDay(serviceDay);
        Customer customer = customerService.getCustomerById(request.getCutomerId());

        Pool pool = new Pool();
        pool.setCustomer(customer);
        pool.setAddress(request.getAddress());
        pool.setServiceDay(serviceDay);
        pool.setPoolType(request.getPoolType());
        pool.setPrice(request.getPrice());
        pool.setChemicalInclude(request.isChemicalInclude());
        pool.setInService(request.isInService());

        Pool newPool = poolService.createPool(pool);

        return ResponseEntity.ok(newPool);
    }

}
