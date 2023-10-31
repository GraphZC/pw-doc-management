package com.poolworldpattaya.docmanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String address;
    private String telephone;
    private String taxId;
}
