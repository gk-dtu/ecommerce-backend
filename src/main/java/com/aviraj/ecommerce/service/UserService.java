package com.aviraj.ecommerce.service;

import com.aviraj.ecommerce.dto.UserRequestDto;
import com.aviraj.ecommerce.dto.UserResponseDto;
import com.aviraj.ecommerce.entity.User;
import com.aviraj.ecommerce.exception.UserNotFoundException;
import com.aviraj.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserResponseDto createUser(UserRequestDto dto) {
        User user = mapToUser(dto);
        repository.save(user);

        //map user to UserResponseDto
        return mapToResponseDto(user);
    }

    public List<UserResponseDto> getAllUsers() {
        return mapToResponseDto(repository.findAll());
    }

    public UserResponseDto updateUser(Long id, UserRequestDto updatedUser) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found, can't update"));

        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());

        repository.save(user);

        return mapToResponseDto(user);
    }

    public void deleteUser(Long id){
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found, can't delete"));

        repository.delete(user);
    }


    //private methods
    private UserResponseDto mapToResponseDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        return dto;
    }

    private List<UserResponseDto> mapToResponseDto(List<User> users) {
        List<UserResponseDto> dto = new ArrayList<>();
        for(User user: users){
            UserResponseDto resUser = new UserResponseDto();
            resUser.setId(user.getId());
            resUser.setName(user.getName());
            resUser.setEmail(user.getEmail());
            dto.add(resUser);
        }

        return dto;
    }

    private User mapToUser(UserRequestDto dto){
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        return user;
    }
}