package com.poolworldpattaya.docmanagement.service;

import com.poolworldpattaya.docmanagement.entity.Product;
import com.poolworldpattaya.docmanagement.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public Product getProductById(UUID id) {
        return productRepository.findById(id).get();
    }

    public Product createProduct(Product product) {
        productRepository.save(product);
        return product;
    }

    public Product deleteProduct(UUID id) {
        Product product = productRepository.findById(id).get();
        productRepository.deleteById(id);
        return product;
    }

    public Product updateProduct(Product product){
        UUID id = product.getId();

        Product record = productRepository.findById(id).get();
        record.setCode(product.getCode());
        record.setName(product.getName());
        record.setDescription(product.getDescription());
        record.setPrice(product.getPrice());
        record.setUnit(product.getUnit());

        productRepository.save(record);
        return record;
    }
}
