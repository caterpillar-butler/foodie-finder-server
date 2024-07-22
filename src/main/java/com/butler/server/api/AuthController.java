package com.butler.server.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.butler.server.model.dto.UserDto;
import com.butler.server.service.UserService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public ResponseEntity<String> createUser(@RequestBody UserDto userDto) {
    log.info(userDto.toString());

    if (userDto.getEmail() == null || userDto.getPassword() == null) {
      return new ResponseEntity<>("Email and Password are required", HttpStatus.BAD_REQUEST);
    }
    userService.createUser(userDto);
    return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
  }
}
