package com.ecommerce.wishlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.wishlist.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
    
}
