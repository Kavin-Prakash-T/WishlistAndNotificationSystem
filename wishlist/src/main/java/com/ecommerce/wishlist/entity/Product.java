package com.ecommerce.wishlist.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String name;
    private String category;
    private double price;
    private String stockStatus; 
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime lastUpdated = LocalDateTime.now();

    /*@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Wishlist> wishlistItems;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Notification> notifications;*/
}
