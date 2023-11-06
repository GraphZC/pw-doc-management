package com.poolworldpattaya.docmanagement.repository;

import com.poolworldpattaya.docmanagement.entity.ServiceDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ServiceDayRepository extends JpaRepository<ServiceDay, UUID> {
}
