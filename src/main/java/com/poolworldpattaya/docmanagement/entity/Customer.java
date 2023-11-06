package com.poolworldpattaya.docmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue
    private UUID id;

    @OneToMany(mappedBy = "customer")
    List<Pool> pools;

    @OneToMany(mappedBy = "customer")
    List<CustomerOrder> customerOrders;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String address;

    @Column(nullable = true)
    private String telephone;

    @Column(nullable = true)
    private String taxId;
}
