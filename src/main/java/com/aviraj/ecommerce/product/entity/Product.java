package com.aviraj.ecommerce.product.entity;

import com.aviraj.ecommerce.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}