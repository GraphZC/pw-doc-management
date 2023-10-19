package com.poolworldpattaya.docmanagement.controller;

import com.poolworldpattaya.docmanagement.entity.Customer;
import com.poolworldpattaya.docmanagement.request.CustomerRequest;
import com.poolworldpattaya.docmanagement.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/test")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String test() {
        return "test";
    }

//    @GetMapping
//    public String getAllCustomer(Model model) {
//        model.addAttribute("customers", customerService.getAllCustomer());
//        return "customer-all";
//    }

    @GetMapping("/edit/{id}")
    public String getOneCustomer(@PathVariable UUID id, Model model) {
        Customer customer = customerService.getOneById(id);
        model.addAttribute("customer", customer);
        return "customer-edit";
    }

    @GetMapping("/add")
    public String getCustomerForm(Model model) {
        return "customer-add";
    }

    @PostMapping("/add")
    public String createCustomer(@RequestBody CustomerRequest customer, Model model) {
        customerService.createCustomer(customer);
        model.addAttribute("customer", customerService.getAllCustomer());
        return "redirect:/customers";
    }

    @PostMapping("/edit/{id}")
    public String updateNameCustomer(@PathVariable UUID id,@RequestBody CustomerRequest customer,String newName, Model model) {
        customerService.updateCustomer(id,customer);
        return "redirect:/customers";
    }

    @PostMapping("/edit/{id}")
    public String updateCustomer(@PathVariable UUID id,@RequestBody CustomerRequest customer, Model model) {
        customerService.updateCustomer(id,customer);
        return "redirect:/customers";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable UUID id) {
        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }

    @GetMapping("/api/v1/customers")
    public String getCustomers(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size, Model model) {
        List<Integer> pageSizes = Arrays.asList(10,25,50,100) ;
        Page<Customer> customerPage = customerService.getPageCustomers(page, size);
        model.addAttribute("customerPage", customerPage);
        model.addAttribute("pageSizes", pageSizes);
        return "customer-all";
    }
}
