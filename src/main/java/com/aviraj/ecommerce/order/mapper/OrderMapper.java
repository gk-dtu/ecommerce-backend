package com.aviraj.ecommerce.order.mapper;

import com.aviraj.ecommerce.order.dto.OrderResponseDto;
import com.aviraj.ecommerce.order.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderResponseDto toOrderResponseDto(Order order) {

        OrderResponseDto dto = new OrderResponseDto();

        dto.setOrderId(order.getId());
        dto.setProductName(order.getProduct().getName());
        dto.setQuantity(order.getQuantity());
        dto.setTotalPrice(order.getTotalPrice());

        return dto;
    }
}
