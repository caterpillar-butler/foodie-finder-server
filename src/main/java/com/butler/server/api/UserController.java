package com.butler.server.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.butler.server.model.entities.User;
import com.butler.server.model.mapper.UserMapper;

@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  private UserMapper userMapper;

  @PostMapping("/create")
  public void createUser(@RequestBody User user) {
    userMapper.insertUser(user);
  }

  @GetMapping("/{id}")
  public User getUserById(@PathVariable Long id) {
    return userMapper.selectUserById(id);
  }

  @GetMapping
  public List<User> getAllUsers() {
    return userMapper.selectAllUsers();
  }

  @PutMapping("/{id}")
  public void updateUser(@PathVariable Long id, @RequestBody User user) {
    user.setId(id);
    userMapper.updateUser(user);
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable Long id) {
    userMapper.deleteUser(id);
  }
}