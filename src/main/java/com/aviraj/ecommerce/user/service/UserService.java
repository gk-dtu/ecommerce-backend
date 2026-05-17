package com.aviraj.ecommerce.user.service;

import com.aviraj.ecommerce.user.dto.UserRequestDto;
import com.aviraj.ecommerce.user.dto.UserResponseDto;
import com.aviraj.ecommerce.user.entity.User;
import com.aviraj.ecommerce.common.exception.UserNotFoundException;
import com.aviraj.ecommerce.user.repository.UserRepository;
import com.aviraj.ecommerce.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;
    private final UserMapper userMapper;

    public UserService(UserRepository repository, UserMapper userMapper) {
        this.repository = repository;
        this.userMapper = userMapper;
    }

    public UserResponseDto createUser(UserRequestDto dto) {
        User user = userMapper.toUser(dto);
        repository.save(user);

        //map user to UserResponseDto
        return userMapper.toResponseDto(user);
    }

    public List<UserResponseDto> getAllUsers() {
        return userMapper.toResponseDto(repository.findAll());
    }

    public UserResponseDto updateUser(Long id, UserRequestDto updatedUser) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found, can't update"));

        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());

        repository.save(user);

        return userMapper.toResponseDto(user);
    }

    public void deleteUser(Long id){
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found, can't delete"));

        repository.delete(user);
    }


}