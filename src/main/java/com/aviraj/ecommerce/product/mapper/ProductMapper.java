package com.aviraj.ecommerce.product.mapper;

import com.aviraj.ecommerce.product.dto.ProductRequestDto;
import com.aviraj.ecommerce.product.dto.ProductResponseDto;
import com.aviraj.ecommerce.product.entity.Product;
import com.aviraj.ecommerce.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ProductMapper{

    public ProductResponseDto toProductResponseDto(Product product) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        return dto;
    }

    public List<ProductResponseDto> toProductResponseDto(List<Product> products) {
        List<ProductResponseDto> result = new ArrayList<>();

        for(Product product: products){
            ProductResponseDto dto = new ProductResponseDto();
            dto.setId(product.getId());
            dto.setName(product.getName());
            dto.setPrice(product.getPrice());
            result.add(dto);
        }
        return result;
    }

    public Product toProduct(ProductRequestDto dto, User user) {
        Product product = new Product();
        product.setUser(user);
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        return product;
    }
}