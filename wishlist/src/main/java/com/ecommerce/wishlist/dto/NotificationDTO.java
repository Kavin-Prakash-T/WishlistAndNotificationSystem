package com.ecommerce.wishlist.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
public class NotificationDTO {
    private Long notificationId;
    private Long userId;
    private String productName;
    private String message;
    private LocalDateTime sentAt;
    private String deliveryStatus; 
}
