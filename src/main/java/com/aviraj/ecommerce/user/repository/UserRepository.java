package com.aviraj.ecommerce.user.repository;

import com.aviraj.ecommerce.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

//DAO(data access object)
public interface UserRepository extends JpaRepository<User, Long> {
}