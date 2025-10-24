package com.ecommerce.wishlist.dto;

import lombok.Data;

@Data
public class WishlistDTO {
    private Long wishlistId;
    private Long userId;
    private String productName;
    private boolean notified;
}
