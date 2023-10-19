package com.poolworldpattaya.docmanagement.controller;

import com.poolworldpattaya.docmanagement.entity.Product;
import com.poolworldpattaya.docmanagement.request.ProductRequest;
import com.poolworldpattaya.docmanagement.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/test")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String test() {
        return "test";
    }
//    @GetMapping
//    public String getAllProducts(Model model) {
//        model.addAttribute("products", productService.getAllProducts());
//        return "product-all";
//    }
    @GetMapping
    public String getProducts(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size, Model model) {
        List<Integer> pageSizes = Arrays.asList(10, 25, 50, 100);
        Page<Product> productPage = productService.getPageProducts(page, size);
        model.addAttribute("productPage", productPage);
        model.addAttribute("pageSizes", pageSizes);
        return "product-all";
    }

    @GetMapping("/edit/{id}")
    public String getOneProduct(@PathVariable UUID id, Model model) {
        Product product = productService.getOneById(id);
        model.addAttribute("product", product);
        return "product-edit";
    }
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    @GetMapping("/add")
    public String getProductForm(Model model) {
        return "product-add";
    }

    @PostMapping("/add")
    public String createProduct(@RequestBody ProductRequest product, Model model) {
        productService.createProduct(product);
//        model.addAttribute("products", productService.getAllProducts());
        return "redirect:/products";
    }
    @PostMapping("/edit/{id}")
    public String updateProduct(@RequestBody ProductRequest product) {
        productService.updateProduct(product);
        return "redirect:/products";
    }
}
