package com.poolworldpattaya.docmanagement.repository;

import com.poolworldpattaya.docmanagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    Employee findByUsername(String username);
}
