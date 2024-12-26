package com.alten.products.service;

import com.alten.products.model.Cart;
import com.alten.products.model.dto.CartSummaryDTO;

public interface CartService {
    CartSummaryDTO getCartForUser();

    void addProductToCart(Long productId, int quantity);

    void removeProductFromCart(Long productId);

    void updateQuantity(Long productId, int quantity);
}
