package com.shop.notification.repository;

import com.shop.notification.model.NotificationModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationModel, Integer> {
}
