package com.poolworldpattaya.docmanagement.service;

import com.poolworldpattaya.docmanagement.entity.Customer;
import com.poolworldpattaya.docmanagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(UUID id) {
        return customerRepository.findById(id).get();
    }

    public Customer createCustomer(Customer customer) {
        customerRepository.save(customer);
        return customer;
    }

    public Customer deleteCustomer(UUID id) {
        Customer customer = customerRepository.findById(id).get();
        customerRepository.deleteById(id);
        return customer;
    }

    public Customer updateCustomer(Customer customer) {
        UUID id = customer.getId();

        Customer record = customerRepository.findById(id).get();
        record.setName(customer.getName());
        record.setAddress(customer.getAddress());
        record.setTelephone(customer.getTelephone());
        record.setTaxId(customer.getTaxId());

        customerRepository.save(record);
        return record;
    }
}
