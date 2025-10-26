package com.ecommerce.wishlist.service;

import com.ecommerce.wishlist.entity.*;
import com.ecommerce.wishlist.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
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

@Autowired
private NotificationRepository notificationRepository;

@Autowired
private WishlistRepository wishlistRepository;

    
        public Product updatePrice(Long productId, double newPrice) {
            Product product = productRepository.findById(productId).orElseThrow();
            double oldPrice = product.getPrice();
            product.setPrice(newPrice);
            product.setLastUpdated(LocalDateTime.now());
            productRepository.save(product);

            if (newPrice < oldPrice) {
                List<Wishlist> wishlistItems = wishlistRepository.findByProductProductId(productId);

                for (Wishlist item : wishlistItems) {
                    if (item.isNotified()) {
                        continue;
                    }

                    User user = item.getUser();

                    Notification notification = new Notification();
                    notification.setUser(user);
                    notification.setProduct(product);
                    notification.setMessage("Price dropped from ₹" + oldPrice + " to ₹" + newPrice + "!");
                    notification.setSentAt(LocalDateTime.now());
                    notification.setDeliveryStatus("Sent");
                    notificationRepository.save(notification);
                    item.setNotified(true);
                    wishlistRepository.save(item);
                }
            }

            return product;
        }
}
