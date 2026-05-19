package com.aviraj.ecommerce.order.controller;

import com.aviraj.ecommerce.common.response.ApiResponse;
import com.aviraj.ecommerce.order.dto.OrderRequestDto;
import com.aviraj.ecommerce.order.dto.OrderResponseDto;
import com.aviraj.ecommerce.order.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public ApiResponse<OrderResponseDto> placeOrder(@Valid @RequestBody OrderRequestDto dto) {
        OrderResponseDto response = service.placeOrder(dto);
        return new ApiResponse<>(true, "Order Placed", response);
    }
}