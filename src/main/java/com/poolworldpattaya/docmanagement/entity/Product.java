package com.poolworldpattaya.docmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = true)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String unit;

    @OneToMany(mappedBy = "product")
    private List<Purchase> purchase;
}
