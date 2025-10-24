package com.ecommerce.wishlist.service;

import com.ecommerce.wishlist.entity.*;
import com.ecommerce.wishlist.repository.*;
import org.springframework.stereotype.Service;
import com.ecommerce.wishlist.dto.WishlistDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishlistService {
    private WishlistRepository wishlistRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;

    public WishlistService(WishlistRepository wishlistRepository, UserRepository userRepository,
            ProductRepository productRepository) {
        this.wishlistRepository = wishlistRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public WishlistDTO addItem(Long userId, Long productId) {
        User user = userRepository.findById(userId).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();

        Wishlist item = new Wishlist();
        item.setUser(user);
        item.setProduct(product);
        item.setNotified(false);
        item.setAddedAt(LocalDateTime.now());

        Wishlist savedItem = wishlistRepository.save(item);
        return mapToDTO(savedItem);
    }

    public WishlistDTO mapToDTO(Wishlist item) {
        WishlistDTO dto = new WishlistDTO();
        dto.setWishlistId(item.getWishlistId());
        dto.setUserId(item.getUser().getUserId());
        dto.setProductName(item.getProduct().getName());
        dto.setNotified(item.isNotified());
        return dto;
    }

    public List<WishlistDTO> getWishlistByUser(Long userId) {
        List<Wishlist> items = wishlistRepository.findByUserUserId(userId);
        return items.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public void removeWishlistItem(Long wishlistId) {
        wishlistRepository.deleteById(wishlistId);
    }
}
