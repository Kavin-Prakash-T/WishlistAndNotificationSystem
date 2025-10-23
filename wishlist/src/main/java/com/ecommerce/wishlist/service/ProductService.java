package com.ecommerce.wishlist.service;

import com.ecommerce.wishlist.entity.Product;
import com.ecommerce.wishlist.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDateTime;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product updateProductPrice(Long productId, double newPrice) {
        Product product = productRepository.findById(productId).orElseThrow();
        product.setPrice(newPrice);
        product.setLastUpdated(LocalDateTime.now());
        return productRepository.save(product);
    }
}
