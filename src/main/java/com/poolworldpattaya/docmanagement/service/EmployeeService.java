package com.poolworldpattaya.docmanagement.service;

import com.poolworldpattaya.docmanagement.entity.Customer;
import com.poolworldpattaya.docmanagement.entity.Employee;
import com.poolworldpattaya.docmanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> userDetail = Optional.ofNullable(employeeRepository.findByUsername(username));

        // Converting userDetail to EmployeeDetails
        return userDetail.map(EmployeeDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public Employee getByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }

    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(UUID id) {
        return employeeRepository.findById(id).get();
    }

    public Employee createEmployee(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    public Employee deleteEmployee(UUID id) {
        Employee employee = employeeRepository.findById(id).get();
        employeeRepository.deleteById(id);
        return employee;
    }

    public Employee updateEmployee(Employee employee) {
        UUID id = employee.getId();

        Employee record = employeeRepository.findById(id).get();
        record.setName(employee.getName());
        record.setPassword(employee.getPassword());
        employeeRepository.save(record);
        return record;
    }
}
