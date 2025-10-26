package com.ecommerce.wishlist.service;

import com.ecommerce.wishlist.dto.NotificationDTO;
import com.ecommerce.wishlist.entity.Notification;
import com.ecommerce.wishlist.repository.NotificationRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {
    private NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<NotificationDTO> getNotificationsByUser(Long userId) {
        List<Notification> notifications = notificationRepository.findByUserUserId(userId);
        return notifications.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public NotificationDTO mapToDTO(Notification notification) {
        NotificationDTO dto = new NotificationDTO();
        dto.setNotificationId(notification.getNotificationId());
        dto.setUserId(notification.getUser().getUserId());
        dto.setProductName(notification.getProduct().getName());
        dto.setMessage(notification.getMessage());
        dto.setSentAt(notification.getSentAt());
        dto.setDeliveryStatus(notification.getDeliveryStatus());
        return dto;
    }
}
