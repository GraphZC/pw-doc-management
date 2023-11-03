package com.poolworldpattaya.docmanagement.controller;


import com.poolworldpattaya.docmanagement.entity.Employee;
import com.poolworldpattaya.docmanagement.request.CreateEmployeeRequest;
import com.poolworldpattaya.docmanagement.request.EditEmployeeRequest;
import com.poolworldpattaya.docmanagement.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/test")
	@Operation(security = { @SecurityRequirement(name = "bearer-key") })
	public String test() {
		return "test";
	}

	@GetMapping("/{id}")
	@Operation(security = { @SecurityRequirement(name = "bearer-key") })
	public ResponseEntity<Employee> getOneEmployee(@PathVariable UUID id) {
		Employee employee = employeeService.getEmployeeById(id);
		if (employee == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(employee);
	}

	@PostMapping("/")
	@Operation(security = { @SecurityRequirement(name = "bearer-key") })
	public ResponseEntity<Employee> createEmployee(@RequestBody CreateEmployeeRequest employee) {
		Employee newEmployee = new Employee();
		newEmployee.setName(employee.getName());
		newEmployee.setUsername(employee.getUsername());
		newEmployee.setPassword(employee.getPassword());
		newEmployee.setRoles(employee.getRoles());

		// Create
		newEmployee = employeeService.createEmployee(newEmployee);
		return ResponseEntity.created(null).body(newEmployee);
	}

	@PutMapping("/{id}")
	@Operation(security = { @SecurityRequirement(name = "bearer-key") })
	public ResponseEntity<Employee> updateEmployee (@PathVariable UUID id, @RequestBody EditEmployeeRequest employee) {
		// Find by id
		Employee record = employeeService.getEmployeeById(id);
		if (record == null) {
			return ResponseEntity.notFound().build();
		}

		record.setName(employee.getName());
		record.setPassword(employee.getPassword());

		// Update
		Employee updated = employeeService.updateEmployee(record);
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	@Operation(security = { @SecurityRequirement(name = "bearer-key") })
	public ResponseEntity<Employee> deleteEmployee(@PathVariable UUID id) {
		// Find by id
		Employee employee = employeeService.getEmployeeById(id);
		if (employee == null) {
			return ResponseEntity.notFound().build();
		}
		// Delete
		Employee deleted = employeeService.deleteEmployee(id);
		return ResponseEntity.ok(deleted);
	}

	@GetMapping("/")
	@Operation(security = { @SecurityRequirement(name = "bearer-key") })
	public ResponseEntity<List<Employee>> getEmployees() {
		List<Employee> employees = employeeService.getAllEmployee();

		return ResponseEntity.ok(employees);
	}
}
