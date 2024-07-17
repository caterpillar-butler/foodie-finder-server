package com.butler.server.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.butler.server.model.entities.User;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @PostMapping("/register")
  public ResponseEntity<String> registerUser(@RequestBody User user) {
    return ResponseEntity.ok("User registered successfully");
  }
}