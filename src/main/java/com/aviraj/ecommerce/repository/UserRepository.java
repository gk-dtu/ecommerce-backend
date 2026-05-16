package com.aviraj.ecommerce.repository;

import com.aviraj.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

//DAO(data access object)
public interface UserRepository extends JpaRepository<User, Long> {
}