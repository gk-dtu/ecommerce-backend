package com.aviraj.ecommerce.order.dto;

import lombok.Data;

@Data
public class OrderResponseDto {

    private Long orderId;
    private String productName;
    private Integer quantity;
    private Double totalPrice;
}