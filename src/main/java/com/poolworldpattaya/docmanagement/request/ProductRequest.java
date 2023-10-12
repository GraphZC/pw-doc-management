package com.poolworldpattaya.docmanagement.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private UUID Id;
    private String name;
    private String code;
    private String description;
    private double price;
    private String unit;
}
