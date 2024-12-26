package com.alten.products.controller;

import com.alten.products.model.Wishlist;
import com.alten.products.model.dto.WishlistSummaryDTO;
import com.alten.products.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wishlist")
@RequiredArgsConstructor
public class WishlistController {

    private final WishListService wishlistService;

    @GetMapping
    public ResponseEntity<WishlistSummaryDTO> getWishlist() {
        return ResponseEntity.ok( wishlistService.getUserWishlistDto());
    }

    @PostMapping("/add/{productId}")
    public ResponseEntity<String> addProductToWishlist(@PathVariable Long productId) {
        wishlistService.addProductToWishlist( productId);
        return ResponseEntity.ok("Produit ajouté à la liste d'envies");
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<String> removeProductFromWishlist(@PathVariable Long productId) {

        wishlistService.removeProductFromWishlist(productId);
        return ResponseEntity.ok("Produit supprimé de la liste d'envies");
    }

}
