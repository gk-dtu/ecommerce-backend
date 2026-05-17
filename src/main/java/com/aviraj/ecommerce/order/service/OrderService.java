package com.aviraj.ecommerce.order.service;

import com.aviraj.ecommerce.common.exception.UserNotFoundException;
import com.aviraj.ecommerce.order.dto.OrderRequestDto;
import com.aviraj.ecommerce.order.dto.OrderResponseDto;
import com.aviraj.ecommerce.order.entity.Order;
import com.aviraj.ecommerce.order.mapper.OrderMapper;
import com.aviraj.ecommerce.order.repository.OrderRepository;
import com.aviraj.ecommerce.product.entity.Product;
import com.aviraj.ecommerce.product.repository.ProductRepository;
import com.aviraj.ecommerce.user.entity.User;
import com.aviraj.ecommerce.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepo;
    private final UserRepository userRepo;
    private final ProductRepository productRepo;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepo,
                        UserRepository userRepo,
                        ProductRepository productRepo,
                        OrderMapper orderMapper) {
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
        this.orderMapper = orderMapper;
    }

    public OrderResponseDto placeOrder(OrderRequestDto dto) {

        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Product product = productRepo.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Order order = new Order();

        order.setUser(user);
        order.setProduct(product);
        order.setQuantity(dto.getQuantity());

        double total = product.getPrice() * dto.getQuantity();
        order.setTotalPrice(total);

        Order saved = orderRepo.save(order);

        return orderMapper.toOrderResponseDto(saved);
    }
}