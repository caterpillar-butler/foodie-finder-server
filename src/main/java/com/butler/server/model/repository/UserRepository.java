package com.butler.server.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.butler.server.model.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
