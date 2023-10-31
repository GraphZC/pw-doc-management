package com.poolworldpattaya.docmanagement.service;

import com.poolworldpattaya.docmanagement.entity.Customer;
import com.poolworldpattaya.docmanagement.repository.CustomerRepository;
import com.poolworldpattaya.docmanagement.request.CustomerRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    public Customer getOneById(UUID id) {
        return customerRepository.findById(id).get();
    }

//    public Customer getOneByName(String name){
//        return customerRepository.findByName(name);
//    }

    public void createCustomer(CustomerRequest customer) {
        Customer record = modelMapper.map(customer, Customer.class);
        customerRepository.save(record);
    }

    public void deleteCustomer(UUID id) {
        customerRepository.deleteById(id);
    }

    public void updateCustomer(UUID id, CustomerRequest customer) {
        Customer record = modelMapper.map(customer ,Customer.class);
        customerRepository.save(record);
    }

    public Page<Customer> getPageCustomers(int page, int size) {
        Pageable pageRequest = PageRequest.of(page, size);
        return customerRepository.findAll(pageRequest);
    }




}
