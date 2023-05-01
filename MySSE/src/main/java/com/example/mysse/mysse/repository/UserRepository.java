package com.example.mysse.mysse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mysse.mysse.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
