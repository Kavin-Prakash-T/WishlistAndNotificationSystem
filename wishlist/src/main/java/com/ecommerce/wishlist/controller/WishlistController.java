package com.ecommerce.wishlist.controller;

import com.ecommerce.wishlist.service.WishlistService;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.wishlist.dto.WishlistDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {
    private WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping
    public ResponseEntity<WishlistDTO> addItem(@RequestParam Long userId, @RequestParam Long productId) {
        WishlistDTO dto = wishlistService.addItem(userId, productId);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<WishlistDTO>> getWishlist( @PathVariable Long userId) {
        List<WishlistDTO> wishlist = wishlistService.getWishlistByUser(userId);
        return ResponseEntity.ok(wishlist);
    }

    @DeleteMapping("/remove/{wishlistId}")
    public ResponseEntity<String> removeWishlistItem(@PathVariable Long wishlistId) {
        wishlistService.removeWishlistItem(wishlistId);
        return ResponseEntity.ok("Wishlist item removed successfully.");
    }
}
