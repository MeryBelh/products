package com.alten.products.service;

import com.alten.products.model.Wishlist;
import com.alten.products.model.WishlistProduct;
import com.alten.products.model.dto.WishlistSummaryDTO;

public interface WishListService {

    WishlistSummaryDTO getUserWishlistDto();


    Wishlist addProductToWishlist(Long productId);


    void removeProductFromWishlist(Long productId);


}
