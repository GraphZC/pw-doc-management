package com.poolworldpattaya.docmanagement.service;

import com.poolworldpattaya.docmanagement.entity.Employee;
import com.poolworldpattaya.docmanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService implements UserDetailsService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public EmployeeDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> userDetail = Optional.ofNullable(repository.findByUsername(username));

        // Converting userDetail to EmployeeDetails
        return userDetail.map(EmployeeDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public Employee getByUsername(String username) {
        return repository.findByUsername(username);
    }
}
