package com.poolworldpattaya.docmanagement;

import com.poolworldpattaya.docmanagement.repository.EmployeeRepository;
import com.poolworldpattaya.docmanagement.service.EmployeeService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    String username;

    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeService employeeService;

    @BeforeAll
    public void setup() {
        username = "Jane Doe";
    }

    @Test
    public void testLoadUserByUsername() {
        when(employeeRepository.findByUsername())
    }
}
