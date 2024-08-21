package com.rohit.ecommerce.repository;

import com.rohit.ecommerce.notification.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}
