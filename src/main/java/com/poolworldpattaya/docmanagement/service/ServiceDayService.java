package com.poolworldpattaya.docmanagement.service;

import com.poolworldpattaya.docmanagement.entity.ServiceDay;
import com.poolworldpattaya.docmanagement.repository.ServiceDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ServiceConfigurationError;
import java.util.UUID;

@Service
public class ServiceDayService {
    @Autowired
    private ServiceDayRepository serviceDayRepository;

    public List<ServiceDay> getAllServiceDay(){
        return getAllServiceDay();
    }

    public ServiceDay getServiceDayById(UUID id){
        ServiceDay serviceDay =  serviceDayRepository.findById(id).get();
        return  serviceDay;
    }

    public ServiceDay createServiceDay(ServiceDay serviceDay){
        serviceDayRepository.save(serviceDay);
        return serviceDay;
    }

    public ServiceDay deleteServiceDay(UUID id){
        ServiceDay serviceDay = serviceDayRepository.findById(id).get();
        serviceDayRepository.deleteById(id);
        return serviceDay;
    }
    public ServiceDay updateServiceDay(ServiceDay serviceDay){
        UUID id = serviceDay.getId();

        ServiceDay record = serviceDayRepository.findById(id).get();
        record.setMonday(serviceDay.isMonday());
        record.setTuesday(serviceDay.isTuesday());
        record.setWednesday(serviceDay.isWednesday());
        record.setThursday(serviceDay.isThursday());
        record.setFriday(serviceDay.isFriday());
        record.setSaturday(record.isSaturday());
        record .setSunday(record.isSunday());

        serviceDayRepository.save(record);
        return record;
    }

}
