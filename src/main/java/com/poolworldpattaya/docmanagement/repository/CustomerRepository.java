package com.poolworldpattaya.docmanagement.repository;

import com.poolworldpattaya.docmanagement.entity.Customer;
import com.poolworldpattaya.docmanagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Customer findByName(String username);

}
