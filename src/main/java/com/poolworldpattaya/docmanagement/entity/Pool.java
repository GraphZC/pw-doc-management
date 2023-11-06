package com.poolworldpattaya.docmanagement.entity;

import com.poolworldpattaya.docmanagement.enums.PoolType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Pool {
    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne(mappedBy = "pool")
    private ServiceDay serviceDay;

    @Column(nullable = false)
    private UUID cutomerId;

    @ManyToOne
    @JoinColumn(name = "cutomerId", referencedColumnName = "id")
    private Customer customer;

    @OneToMany(mappedBy = "pool")
    private List<Purchase> purchases;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private boolean chemicalInclude;

    @Column(nullable = false)
    private PoolType poolType;

    @Column(nullable = false)
    private boolean inService;

}
