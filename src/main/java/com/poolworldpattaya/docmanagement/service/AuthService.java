package com.poolworldpattaya.docmanagement.service;

import com.poolworldpattaya.docmanagement.entity.Employee;
import com.poolworldpattaya.docmanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String addUser(Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        repository.save(employee);

        return "User added successfully";
    }
}
