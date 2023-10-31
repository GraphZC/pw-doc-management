package com.poolworldpattaya.docmanagement.service;

import com.poolworldpattaya.docmanagement.entity.Product;
import com.poolworldpattaya.docmanagement.repository.ProductRepository;
import com.poolworldpattaya.docmanagement.request.ProductRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

//    public Page<Product> getPageProducts(int page, int size) {
//        Pageable pageRequest = PageRequest.of(page, size);
//        return productRepository.findAll(pageRequest);
//    }

    public Product getOneById(UUID id) {
        return productRepository.findById(id).get();
    }

    public void createProduct(ProductRequest product) {
        Product record = modelMapper.map(product, Product.class);
        productRepository.save(record);
    }
    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);

    }
    public void updateProduct(ProductRequest product){
        Product record = modelMapper.map(product, Product.class);
        productRepository.save(record);
    }
}
