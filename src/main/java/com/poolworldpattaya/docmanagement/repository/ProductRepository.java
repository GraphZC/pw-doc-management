package com.poolworldpattaya.docmanagement.repository;


import com.poolworldpattaya.docmanagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
