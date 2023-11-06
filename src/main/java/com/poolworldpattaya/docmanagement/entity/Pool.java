package com.poolworldpattaya.docmanagement.entity;

import com.poolworldpattaya.docmanagement.enums.PoolType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Pool {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private UUID cutomerId;

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
