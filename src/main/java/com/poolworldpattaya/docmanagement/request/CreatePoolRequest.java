package com.poolworldpattaya.docmanagement.request;

import com.poolworldpattaya.docmanagement.enums.PoolType;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePoolRequest {

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

    @Column(nullable = false)
    private boolean monday;

    @Column(nullable = false)
    private boolean tuesday;

    @Column(nullable = false)
    private boolean wednesday;

    @Column(nullable = false)
    private boolean thursday;

    @Column(nullable = false)
    private boolean friday;

    @Column(nullable = false)
    private boolean saturday;

    @Column(nullable = false)
    private boolean sunday;
}
