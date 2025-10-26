package com.ecommerce.wishlist.repository;

import com.ecommerce.wishlist.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface NotificationRepository extends JpaRepository<Notification, Long> {
     List<Notification> findByUserUserId(Long userId);
}
