package com.ecommerce.wishlist.controller;

import com.ecommerce.wishlist.entity.Product;
import com.ecommerce.wishlist.service.ProductService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/products")
public class ProductController {
    private ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PutMapping("/update/{productId}")
    public Product updatePrice(@PathVariable Long productId, @RequestParam double price) {
        return productService.updateProductPrice(productId, price);
    }
}
