package com.aviraj.ecommerce.order.controller;

import com.aviraj.ecommerce.order.dto.OrderRequestDto;
import com.aviraj.ecommerce.order.dto.OrderResponseDto;
import com.aviraj.ecommerce.order.service.OrderService;
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
    public OrderResponseDto placeOrder(@RequestBody OrderRequestDto dto) {
        return service.placeOrder(dto);
    }
}