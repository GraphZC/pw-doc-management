package com.poolworldpattaya.docmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class ServiceDay {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private UUID poolid;

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
