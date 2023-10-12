package com.poolworldpattaya.docmanagement.service;

import com.poolworldpattaya.docmanagement.entity.Product;
import com.poolworldpattaya.docmanagement.repository.ProductRepository;
import com.poolworldpattaya.docmanagement.request.ProductRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getOneById(UUID id) {
        return productRepository.findById(id).get();
    }

    public void createProduct(ProductRequest product) {
        Product record = modelMapper.map(product, Product.class);
        if (product.getCode().equals("")){
            record.setCode("-");
        }if (product.getDescription().equals("")) {
            record.setDescription("-");
        }
        productRepository.save(record);
    }
    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);

    }
    public void updateProduct(@PathVariable UUID id, ProductRequest product){
        Product record = modelMapper.map(product, Product.class);
        if (product.getCode().equals("")){
            record.setCode("-");
        }if (product.getDescription().equals("")) {
            record.setDescription("-");
        }
        productRepository.save(record);
    }
}
