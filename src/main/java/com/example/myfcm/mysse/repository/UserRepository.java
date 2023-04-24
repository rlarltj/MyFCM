package com.example.myfcm.mysse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myfcm.mysse.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
