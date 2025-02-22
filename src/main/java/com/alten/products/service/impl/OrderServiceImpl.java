package com.alten.products.service.impl;

import com.alten.products.exception.ProductNotFoundException;
import com.alten.products.model.Order;
import com.alten.products.model.OrderProduct;
import com.alten.products.model.Product;
import com.alten.products.model.dto.OrderSummaryDTO;
import com.alten.products.repository.OrderProductRepository;
import com.alten.products.repository.OrderRepository;
import com.alten.products.repository.ProductRepository;
import com.alten.products.service.OrderService;
import com.alten.products.utils.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

        private final OrderRepository orderRepository;
        private final OrderProductRepository orderProductRepository;
        private final ProductRepository productRepository;
        private final UserUtil userUtil;


        public Order getUserOrderlist() {
            return orderRepository.findByUserId(userUtil.getCurrentUser().getId())
                    .orElseGet(() -> {
                        Order wishlist = Order.builder()
                                .userId(userUtil.getCurrentUser().getId())
                                .build();
                        return orderRepository.save(wishlist);
                    });
        }

    @Override
    public OrderSummaryDTO getUserOrderDto() {

        Order order = orderRepository.findByUserId(userUtil.getCurrentUser().getId())
                .orElseGet(() -> {
                    Order wishlist = Order.builder()
                            .userId(userUtil.getCurrentUser().getId())
                            .build();
                    return orderRepository.save(wishlist);
                });

        List<Product> productList = order.getProducts().stream()
                .map(wishlistProduct -> {
                    Product product = productRepository.findById(wishlistProduct.getProductId())
                            .orElseThrow(() -> new ProductNotFoundException("Product not found"));
                    return product;
                })
                .collect(Collectors.toList());

        return new OrderSummaryDTO(order.getId(), productList, order.getStatus());
    }


    public Order addProductToOrder(Long productId) {
        Order orderlist = getUserOrderlist();

        OrderProduct product = OrderProduct.builder()
                    .order(orderlist)
                    .productId(productId)
                    .build();

            orderlist.getProducts().add(product);
            return orderRepository.save(orderlist);
        }
        @Transactional
        public void removeProductFromOrder(Long productId) {
            Order orderlist = getUserOrderlist();
            orderProductRepository.deleteByOrderIdAndProductId(orderlist.getId(), productId);
        }


    }


