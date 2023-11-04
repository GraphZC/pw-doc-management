package com.poolworldpattaya.docmanagement.controller;


import com.poolworldpattaya.docmanagement.entity.Employee;
import com.poolworldpattaya.docmanagement.request.CreateEmployeeRequest;
import com.poolworldpattaya.docmanagement.request.EditEmployeeRequest;
import com.poolworldpattaya.docmanagement.response.EmployeeResponse;
import com.poolworldpattaya.docmanagement.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
	public ResponseEntity<EmployeeResponse> getOneEmployee(@PathVariable UUID id) {
		Employee employee = employeeService.getEmployeeById(id);
		if (employee == null) {
			return ResponseEntity.notFound().build();
		}
		EmployeeResponse employeeResponse = new EmployeeResponse(employee.getId(), employee.getUsername(), employee.getName(), employee.getRoles());
		return ResponseEntity.ok(employeeResponse);
	}

	@PostMapping("/")
	@Operation(security = { @SecurityRequirement(name = "bearer-key") })
	public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody CreateEmployeeRequest employee) {
		Employee newEmployee = new Employee();
		newEmployee.setName(employee.getName());
		newEmployee.setUsername(employee.getUsername());
		newEmployee.setPassword(employee.getPassword());
		newEmployee.setRoles(employee.getRoles());

		// Create
		newEmployee = employeeService.createEmployee(newEmployee);
		EmployeeResponse employeeResponse = new EmployeeResponse(newEmployee.getId(), newEmployee.getUsername(), newEmployee.getName(), newEmployee.getRoles());
		return ResponseEntity.created(null).body(employeeResponse);
	}

	@PutMapping("/{id}")
	@Operation(security = { @SecurityRequirement(name = "bearer-key") })
	public ResponseEntity<EmployeeResponse> updateEmployee (@PathVariable UUID id, @RequestBody EditEmployeeRequest employee) {
		// Find by id
		Employee record = employeeService.getEmployeeById(id);
		if (record == null) {
			return ResponseEntity.notFound().build();
		}

		record.setName(employee.getName());
		record.setPassword(employee.getPassword());
		record.setRoles(employee.getRole());

		// Update
		Employee updated = employeeService.updateEmployee(record);
		EmployeeResponse employeeResponse = new EmployeeResponse(updated.getId(), updated.getUsername(), updated.getName(), updated.getRoles());
		return ResponseEntity.ok(employeeResponse);
	}

	@DeleteMapping("/{id}")
	@Operation(security = { @SecurityRequirement(name = "bearer-key") })
	public ResponseEntity<EmployeeResponse> deleteEmployee(@PathVariable UUID id) {
		// Find by id
		Employee employee = employeeService.getEmployeeById(id);
		if (employee == null) {
			return ResponseEntity.notFound().build();
		}
		// Delete
		Employee deleted = employeeService.deleteEmployee(id);
		EmployeeResponse employeeResponse = new EmployeeResponse(deleted.getId(), deleted.getUsername(), deleted.getName(), deleted.getRoles());
		return ResponseEntity.ok(employeeResponse);
	}

	@GetMapping("/")
	@Operation(security = { @SecurityRequirement(name = "bearer-key") })
	public ResponseEntity<List<EmployeeResponse>> getEmployees() {
		ArrayList<EmployeeResponse> employeeResponses = new ArrayList<>();
		List<Employee> employees = employeeService.getAllEmployee();
		for (Employee employee: employees) {
			employeeResponses.add(new EmployeeResponse(employee.getId(), employee.getUsername(), employee.getName(), employee.getRoles()));
		}
		return ResponseEntity.ok(employeeResponses);
	}
}
