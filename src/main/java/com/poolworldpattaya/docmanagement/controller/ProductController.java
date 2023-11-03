package com.poolworldpattaya.docmanagement.controller;

import com.poolworldpattaya.docmanagement.entity.Product;
import com.poolworldpattaya.docmanagement.request.CreateProductRequest;
import com.poolworldpattaya.docmanagement.request.EditProductRequest;
import com.poolworldpattaya.docmanagement.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<Product> getOneProduct(@PathVariable UUID id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping("/")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest product) {
        Product newProduct = new Product();
        newProduct.setCode(product.getCode());
        newProduct.setName(product.getName());
        newProduct.setDescription(product.getDescription());
        newProduct.setPrice(product.getPrice());
        newProduct.setUnit(product.getUnit());

        // Create product
        newProduct = productService.createProduct(newProduct);
        return ResponseEntity.created(null).body(newProduct);
    }

    @PutMapping("/{id}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<Product> updateProduct(@PathVariable UUID id, @RequestBody EditProductRequest product) {
        // Find product by id
        Product record = productService.getProductById(id);
        if (record == null) {
            return ResponseEntity.notFound().build();
        }
        if (product.getCode() != null)
            record.setCode(product.getCode());

        record.setName(product.getName());

        if (product.getDescription() != null)
            record.setDescription(product.getDescription());

        record.setPrice(product.getPrice());

        record.setUnit(product.getUnit());

        // Update product
        Product updated = productService.updateProduct(record);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<Product> deleteProduct(@PathVariable UUID id) {
        // Find product by id
        Product product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        // Delete product
        Product deleted = productService.deleteProduct(id);
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("/")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productService.getAllProduct();

        return ResponseEntity.ok(products);
    }

}
