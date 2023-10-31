package com.poolworldpattaya.docmanagement.controller;

import com.poolworldpattaya.docmanagement.entity.Customer;
import com.poolworldpattaya.docmanagement.request.EditCustomerRequest;
import com.poolworldpattaya.docmanagement.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/{id}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Customer> getOneCustomer(@PathVariable UUID id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }

    @PostMapping("/")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer newCustomer = customerService.createCustomer(customer);
        return ResponseEntity.created(null).body(newCustomer);
    }

    @PutMapping("/{id}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Customer> updateCustomer(@PathVariable UUID id, @RequestBody EditCustomerRequest customer) {
        // Find customer by id
        Customer record = customerService.getCustomerById(id);
        if (record == null) {
            return ResponseEntity.notFound().build();
        }

        record.setName(customer.getName());

        if (customer.getAddress() != null)
            record.setAddress(customer.getAddress());

        if (customer.getTelephone() != null)
            record.setTelephone(customer.getTelephone());

        if (customer.getTaxId() != null)
            record.setTaxId(customer.getTaxId());

        // Update customer
        Customer updated = customerService.updateCustomer(record);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable UUID id) {
        // Find customer by id
        Customer customer = customerService.getCustomerById(id);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        // Delete customer
        Customer deleted = customerService.deleteCustomer(id);
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("/")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<List<Customer>> getCustomers() {
        List<Customer> customers = customerService.getAllCustomer();

        return ResponseEntity.ok(customers);
    }
}
