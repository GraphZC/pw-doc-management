package com.poolworldpattaya.docmanagement.entity;

import jakarta.persistence.Entity;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Customer {
    private UUID Id;
    private String customerCode;
    private String customerName;
    private double price;
    private String unit;
}
