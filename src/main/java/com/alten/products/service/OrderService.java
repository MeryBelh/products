package com.alten.products.service;


import com.alten.products.model.Order;
import com.alten.products.model.dto.OrderSummaryDTO;

public interface OrderService {

    OrderSummaryDTO getUserOrderDto();


    Order addProductToOrder(Long productId);


    void removeProductFromOrder(Long productId);


}
