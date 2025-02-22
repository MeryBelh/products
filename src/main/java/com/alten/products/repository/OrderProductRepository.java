package com.alten.products.repository;

import com.alten.products.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
    void deleteByOrderIdAndProductId(Long orderId, Long productId);

}