package com.example.myfcm.mysse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myfcm.mysse.domain.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
