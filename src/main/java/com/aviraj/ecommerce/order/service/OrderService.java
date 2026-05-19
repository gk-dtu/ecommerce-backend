package com.aviraj.ecommerce.order.service;

import com.aviraj.ecommerce.common.exception.ProductNotFoundException;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepo;
    private final UserRepository userRepo;
    private final ProductRepository productRepo;
    private final OrderMapper orderMapper;
    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

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
        logger.info("placing order with User id: {} and product id: {}", dto.getUserId(), dto.getProductId());
        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> {
                    logger.error("Error with placing order user not found id: {}", dto.getUserId());
                    return new UserNotFoundException("User not found");
                });

        Product product = productRepo.findById(dto.getProductId())
                .orElseThrow(() -> {
                    logger.error("Error with placing order product not found id: {}", dto.getProductId());
                    return new ProductNotFoundException("Product not found");
                });

        Order order = new Order();

        order.setUser(user);
        order.setProduct(product);
        order.setQuantity(dto.getQuantity());

        double total = product.getPrice() * dto.getQuantity();
        order.setTotalPrice(total);

        Order saved = orderRepo.save(order);
        logger.info("Order Placed successfully with order id: {}", saved.getId());
        return orderMapper.toOrderResponseDto(saved);
    }
}