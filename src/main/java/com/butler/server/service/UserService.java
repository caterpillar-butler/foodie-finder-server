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

    /**
     * Creates a new user from the provided UserDto.
     *
     * @param userDto the data transfer object containing user information
     * @return the created User entity
     */
    public User createUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setPhone(userDto.getPhone());
        user.setBirth(userDto.getBirth());
        user.setGender(userDto.getGender());

        return userRepository.save(user);
    }

    /**
     * Checks if the given email already exists in the system.
     *
     * @param email the email address to check
     * @return true if the email exists, false otherwise
     */
    public boolean isEmailExist(String email) {
        return userMapper.isEmailExist(email);
    }

    /**
     * Checks if the given phone number already exists in the system.
     *
     * @param phone the phone number to check
     * @return true if the phone number exists, false otherwise
     */
    public boolean isPhoneExist(String phone) {
        return userMapper.isPhoneExist(phone);
    }
}