package com.alten.products.service.impl;

import com.alten.products.exception.ProductNotFoundException;
import com.alten.products.model.Product;
import com.alten.products.model.Wishlist;
import com.alten.products.model.WishlistProduct;
import com.alten.products.model.dto.WishlistSummaryDTO;
import com.alten.products.repository.ProductRepository;
import com.alten.products.repository.WishlistProductRepository;
import com.alten.products.repository.WishlistRepository;
import com.alten.products.service.WishListService;
import com.alten.products.utils.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishListService {

        private final WishlistRepository wishlistRepository;
        private final WishlistProductRepository wishlistProductRepository;
        private final ProductRepository productRepository;
        private final UserUtil userUtil;


        public Wishlist getUserWishlist() {
            return wishlistRepository.findByUserId(userUtil.getCurrentUser().getId())
                    .orElseGet(() -> {
                        Wishlist wishlist = Wishlist.builder()
                                .userId(userUtil.getCurrentUser().getId())
                                .build();
                        return wishlistRepository.save(wishlist);
                    });
        }

    @Override
    public WishlistSummaryDTO getUserWishlistDto() {

        Wishlist wishlistEntity = wishlistRepository.findByUserId(userUtil.getCurrentUser().getId())
                .orElseGet(() -> {
                    Wishlist wishlist = Wishlist.builder()
                            .userId(userUtil.getCurrentUser().getId())
                            .build();
                    return wishlistRepository.save(wishlist);
                });

        List<Product> productList = wishlistEntity.getProducts().stream()
                .map(wishlistProduct -> {
                    Product product = productRepository.findById(wishlistProduct.getProductId())
                            .orElseThrow(() -> new ProductNotFoundException("Product not found"));
                    return product;
                })
                .collect(Collectors.toList());

        return new WishlistSummaryDTO(wishlistEntity.getId(), productList);
    }


    public Wishlist addProductToWishlist(Long productId) {
            Wishlist wishlist = getUserWishlist();

            WishlistProduct product = WishlistProduct.builder()
                    .wishlist(wishlist)
                    .productId(productId)
                    .build();

            wishlist.getProducts().add(product);
            return wishlistRepository.save(wishlist);
        }
        @Transactional
        public void removeProductFromWishlist(Long productId) {
            Wishlist wishlist = getUserWishlist();
            wishlistProductRepository.deleteByWishlistIdAndProductId(wishlist.getId(), productId);
        }


    }


