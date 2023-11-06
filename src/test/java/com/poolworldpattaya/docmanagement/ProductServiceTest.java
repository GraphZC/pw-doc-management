package com.poolworldpattaya.docmanagement;

import com.poolworldpattaya.docmanagement.entity.Product;
import com.poolworldpattaya.docmanagement.repository.ProductRepository;
import com.poolworldpattaya.docmanagement.service.ProductService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.parameters.P;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    String name;

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    @Test
    public void createProduct() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(10.0);
        product.setUnit("Each");
        when(productRepository.save(product)).thenReturn(product);

        Product createdProduct = productService.createProduct(product);
        assertNotNull(createdProduct);
        assertEquals("Test Product", createdProduct.getName());
        assertEquals(10.0, createdProduct.getPrice(), 0.001); // ใช้ delta เทียบ
        assertEquals("Each", createdProduct.getUnit());
    }

    @Test
    void deleteProduct() {
        UUID productId = UUID.randomUUID(); // เจนเลข
        Product product = new Product();
        product.setId(productId);

        when(productRepository.findById(productId)).thenReturn(java.util.Optional.of(product));

        Product deletedProduct = productService.deleteProduct(productId); // เรียก delete product

        assertNotNull(deletedProduct); // ไม่เป็น null และ ลบถูกไอดี
        assertEquals(productId, deletedProduct.getId());

        verify(productRepository, times(1)).deleteById(productId); // จนครั้งที่ deletebyid ถูกเรียกโดย pr

    }

    @Test
    void updateProduct() {
        UUID productId = UUID.randomUUID(); // เจนเลข
        Product existingProduct = new Product();
        existingProduct.setId(productId);
        existingProduct.setCode("123");
        existingProduct.setName("Test Product");
        existingProduct.setDescription("Description");
        existingProduct.setPrice(10.0);
        existingProduct.setUnit("Each");

        Product updatedProduct = new Product();
        updatedProduct.setId(productId);
        updatedProduct.setCode("456");
        updatedProduct.setName("Updated Product");
        updatedProduct.setDescription("Updated");
        updatedProduct.setPrice(15.0);
        updatedProduct.setUnit("Pack");

        when(productRepository.findById(productId)).thenReturn(java.util.Optional.of(existingProduct));
        // เอา productID ไปหาใน existingProduct จาก productRepo ด้วย findByID

        when(productRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0)); // To return the saved product
        // thenAnswer คือคำตอบที่ควรได้หลัง productrepo ทำ save
        Product result = productService.updateProduct(updatedProduct);

        assertNotNull(result);
        assertEquals(productId, result.getId());
        assertEquals("456", result.getCode());
        assertEquals("Updated Product", result.getName());
        assertEquals("Updated", result.getDescription());
        assertEquals(15.0, result.getPrice(), 0.001); // ใช้ delta เทียบ
        assertEquals("Pack", result.getUnit());
    }
}