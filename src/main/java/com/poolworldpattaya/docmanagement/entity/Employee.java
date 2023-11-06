package com.poolworldpattaya.docmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue
    private UUID id;

    @OneToMany(mappedBy = "employee")
    private List<Order> orders;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String roles;
}
