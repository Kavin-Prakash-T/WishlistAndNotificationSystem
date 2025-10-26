package com.ecommerce.wishlist.controller;

import com.ecommerce.wishlist.entity.Product;
import com.ecommerce.wishlist.entity.User;
import com.ecommerce.wishlist.service.ProductService;
import com.ecommerce.wishlist.service.NotificationService;
import com.ecommerce.wishlist.service.UserService;
import com.ecommerce.wishlist.service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ViewController {

    private final WishlistService wishlistService;
    private final UserService userService;
    private final ProductService productService;
    private final NotificationService notificationService;

    public ViewController(WishlistService wishlistService, UserService userService, ProductService productService, NotificationService notificationService) {
        this.wishlistService = wishlistService;
        this.userService = userService;
        this.productService = productService;
        this.notificationService = notificationService;
    }

    @GetMapping("/")
    public String home(Model model) {
        var products = productService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("productCount", products.size());
        return "index";
    }

    @GetMapping("/users")
    public String users(Model model) {
        var users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/wishlist")
    public String wishlist(@RequestParam(name = "userId", required = false) Long userId, Model model) {
        model.addAttribute("users", userService.getAllUsers());
        if (userId != null) {
            model.addAttribute("wishlistItems", wishlistService.getWishlistByUser(userId));
        }
        return "wishlist";
    }

    @GetMapping("/notifications")
    public String notifications(@RequestParam(name = "userId", required = false) Long userId, Model model) {
        model.addAttribute("users", userService.getAllUsers());
        if (userId != null) {
            model.addAttribute("notifications", notificationService.getNotificationsByUser(userId));
        }
        return "notifications";
    }

    @PostMapping("/wishlist/remove/{wishlistId}")
    public String removeWishlist(@PathVariable Long wishlistId,
            @RequestParam(name = "userId", required = false) Long userId) {
        wishlistService.removeWishlistItem(wishlistId);
        if (userId != null) {
            return "redirect:/wishlist?userId=" + userId;
        }
        return "redirect:/wishlist";
    }

    @PostMapping("/users/create")
    public String createUser(@RequestParam String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String notificationPreference) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setNotificationPreference(notificationPreference);
        userService.createUser(user);
        return "redirect:/users";
    }

    @PostMapping("/products/create")
    public String createProduct(@RequestParam String name,@RequestParam(required = false) String category,@RequestParam double price) {
        Product product = new Product();
        product.setName(name);
        product.setCategory(category);
        product.setPrice(price);
        productService.addProduct(product);
        return "redirect:/";
    }

    @PostMapping("/wishlist/add")
    public String addToWishlist(@RequestParam Long userId, @RequestParam Long productId, RedirectAttributes redirectAttributes) {
        wishlistService.addItem(userId, productId);
        redirectAttributes.addFlashAttribute("msg", "Product added to wishlist successfully!");
        return "redirect:/";
    }

    @PostMapping("/products/update/{productId}")
    public String updateProductPrice(@PathVariable Long productId, @RequestParam double price, RedirectAttributes redirectAttributes) {
        productService.updatePrice(productId, price);
        redirectAttributes.addFlashAttribute("msg", "Price updated successfully!");
        return "redirect:/";
    }
}
