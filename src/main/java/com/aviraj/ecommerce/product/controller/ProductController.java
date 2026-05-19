package com.aviraj.ecommerce.product.controller;

import com.aviraj.ecommerce.common.response.ApiResponse;
import com.aviraj.ecommerce.product.dto.ProductRequestDto;
import com.aviraj.ecommerce.product.dto.ProductResponseDto;
import com.aviraj.ecommerce.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
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
    public ApiResponse<ProductResponseDto> createProduct(@Valid @RequestBody ProductRequestDto dto) {
        ProductResponseDto response = service.createProduct(dto);
        return new ApiResponse<>(true, "Product created", response);
    }

    @GetMapping
    public Page<ProductResponseDto> getAllProducts(
            @Valid @RequestParam(defaultValue = "0") int page,
            @Valid @RequestParam(defaultValue = "5") int size) {
        return service.getAllProducts(page, size);
    }

    @GetMapping("/search")
    public List<ProductResponseDto> searchProducts(@Valid @RequestParam String keyword) {
        return service.searchProducts(keyword);
    }

//    @GetMapping
//    public List<ProductResponseDto> getAllProducts() {
//        return service.getAllProducts();
//    }

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