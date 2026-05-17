package com.aviraj.ecommerce.product.controller;

import com.aviraj.ecommerce.product.dto.ProductRequestDto;
import com.aviraj.ecommerce.product.dto.ProductResponseDto;
import com.aviraj.ecommerce.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ProductResponseDto createProduct(@Valid @RequestBody ProductRequestDto dto) {
        return service.createProduct(dto);
    }

    @GetMapping
    public List<ProductResponseDto> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@Valid @PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@Valid @PathVariable Long id) {
        service.deleteProduct(id);
        return "product deleted successfully";
    }
}