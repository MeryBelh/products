package com.alten.products.repository;

import com.alten.products.model.Wishlist;
import com.alten.products.model.WishlistProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishlistProductRepository extends JpaRepository<WishlistProduct, Long> {
    void deleteByWishlistIdAndProductId(Long wishlistId, Long productId);

}