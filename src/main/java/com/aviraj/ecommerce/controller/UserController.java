package com.aviraj.ecommerce.controller;

import com.aviraj.ecommerce.dto.UserRequestDto;
import com.aviraj.ecommerce.dto.UserResponseDto;
import com.aviraj.ecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public UserResponseDto createUser(@Valid @RequestBody UserRequestDto dto) {
        return service.createUser(dto);
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return service.getAllUsers();
    }

    @PutMapping("/{id}")
    public UserResponseDto updateUser(@Valid @PathVariable Long id, @RequestBody UserRequestDto dto) {
        return service.updateUser(id, dto);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id){
        service.deleteUser(id);
        return "User deleted successfully";
    }
}