package com.poolworldpattaya.docmanagement.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditProductRequest {
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
}
