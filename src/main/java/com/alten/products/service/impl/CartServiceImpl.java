package com.alten.products.service.impl;

import com.alten.products.exception.ProductNotFoundException;
import com.alten.products.exception.ResourceNotFoundException;
import com.alten.products.model.Cart;
import com.alten.products.model.CartItem;
import com.alten.products.model.Product;
import com.alten.products.model.User;
import com.alten.products.model.dto.CartSummaryDTO;
import com.alten.products.repository.CartItemRepository;
import com.alten.products.repository.CartRepository;
import com.alten.products.repository.ProductRepository;
import com.alten.products.service.CartService;
import com.alten.products.utils.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    private final CartItemRepository cartItemRepository;

    private final UserUtil userUtil;

    public CartSummaryDTO getCartForUser() {
        Cart cart = Optional.ofNullable(cartRepository.findByUserId(userUtil.getCurrentUser().getId()))
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for user"));

        return new CartSummaryDTO(cart.getId(), cart.getItems().stream()
                .map(CartItem::getProduct)
                .collect(Collectors.toList()));
    }

    public void addProductToCart(Long productId, int quantity) {
        Cart cart = getOrCreateCart();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        findCartItemByProductId(cart, productId)
                .ifPresentOrElse(
                        cartItem -> updateCartItemQuantity(cartItem, quantity),
                        () -> addNewProductToCart(cart, product, quantity)
                );
        cartRepository.save(cart);
    }

    private Cart getOrCreateCart() {
        return Optional.ofNullable(cartRepository.findByUserId(userUtil.getCurrentUser().getId()))
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setUser(userUtil.getCurrentUser());
                    return cartRepository.save(cart);
                });
    }

    private Optional<CartItem> findCartItemByProductId(Cart cart, Long productId) {
        return cart.getItems().stream()
                .filter(cartItem -> cartItem.getProduct().getId().equals(productId))
                .findFirst();
    }

    private void updateCartItemQuantity(CartItem cartItem, int quantity) {
        cartItem.setQuantity(cartItem.getQuantity() + quantity);
        cartItemRepository.save(cartItem);
    }

    private void addNewProductToCart(Cart cart, Product product, int quantity) {
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setCart(cart);
        cartItemRepository.save(cartItem);
        cart.getItems().add(cartItem);
    }


    public void removeProductFromCart(Long productId) {
        Cart cart = Optional.ofNullable(cartRepository.findByUserId(userUtil.getCurrentUser().getId()))
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for the user"));
        CartItem cartItem = Optional.ofNullable(cartItemRepository.findByCartIdAndProductId(cart.getId(), productId))
                .orElseThrow(() -> new ResourceNotFoundException("Product not found in the cart"));
        cart.getItems().remove(cartItem);
        cartItemRepository.delete(cartItem);
        cartRepository.save(cart);
    }


    public void updateQuantity(Long productId, int quantity) {
        Cart cart = Optional.ofNullable(cartRepository.findByUserId(userUtil.getCurrentUser().getId()))
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for the user"));
        CartItem cartItem = Optional.ofNullable(cartItemRepository.findByCartIdAndProductId(cart.getId(), productId))
                .orElseThrow(() -> new ResourceNotFoundException("Product not found in the cart"));
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
    }
}
