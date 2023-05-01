package com.example.mysse.mysse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mysse.mysse.domain.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
