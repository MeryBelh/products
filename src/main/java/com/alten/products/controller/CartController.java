package com.alten.products.controller;

import com.alten.products.model.Cart;
import com.alten.products.model.dto.CartSummaryDTO;
import com.alten.products.service.CartService;
import com.alten.products.service.impl.CartServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<CartSummaryDTO> getCart() {
        return ResponseEntity.ok(cartService.getCartForUser());
    }

    @PostMapping("/add/{productId}")
    public ResponseEntity<String> addProductToCart(@PathVariable Long productId,
                                                   @RequestParam int quantity) {
        cartService.addProductToCart( productId, quantity);
        return ResponseEntity.ok("Product added to the cart");
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<String> removeProductFromCart(@PathVariable Long productId) {
        cartService.removeProductFromCart( productId);
        return ResponseEntity.ok("Produit supprimé du panier");
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<String> updateProductQuantity(@PathVariable Long productId,
                                                        @RequestParam int quantity) {
        cartService.updateQuantity(productId, quantity);
        return ResponseEntity.ok("Quantité mise à jour");
    }
}
