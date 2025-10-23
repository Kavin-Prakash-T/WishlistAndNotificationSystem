package com.ecommerce.wishlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.wishlist.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
