package com.aviraj.ecommerce.product.repository;

import com.aviraj.ecommerce.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}