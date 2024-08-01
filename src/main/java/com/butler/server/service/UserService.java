package com.butler.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.butler.server.model.dto.UserDto;
import com.butler.server.model.entities.User;
import com.butler.server.model.mapper.UserMapper;
import com.butler.server.model.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    public User createUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setPhone(userDto.getPhone());
        user.setBirth(userDto.getBirth());
        user.setGender(userDto.getGender());

        return userRepository.save(user);
    }

    public boolean isEmailExist(String email) {
        return userMapper.isEmailExist(email);
    }
}