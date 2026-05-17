package com.aviraj.ecommerce.user.controller;

import com.aviraj.ecommerce.user.dto.UserRequestDto;
import com.aviraj.ecommerce.user.dto.UserResponseDto;
import com.aviraj.ecommerce.user.service.UserService;
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
    public UserResponseDto updateUser(@Valid @PathVariable Long id, @Valid @RequestBody UserRequestDto dto) {
        return service.updateUser(id, dto);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id){
        service.deleteUser(id);
        return "User deleted successfully";
    }
}