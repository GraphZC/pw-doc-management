package com.poolworldpattaya.docmanagement.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {
    private String customerName;
    private double price;
    private String unit;
    private String customerCode;
}
