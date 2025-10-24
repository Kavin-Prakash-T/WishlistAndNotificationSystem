package com.ecommerce.wishlist.repository;

import com.ecommerce.wishlist.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    List<Wishlist> findByUserUserId(Long userId);
}
