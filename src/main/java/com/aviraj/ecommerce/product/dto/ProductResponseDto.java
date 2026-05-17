package com.aviraj.ecommerce.product.dto;

import lombok.Data;

@Data
public class ProductResponseDto {
    private Long id;
    private String name;
    private double price;
}
