package com.poolworldpattaya.docmanagement.request;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductRequest {
    private UUID Id;
    private String name;
    private String code;
    private String description;
    private String price;
    private String unit;
}
