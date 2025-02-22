package com.alten.products.controller;

import com.alten.products.model.dto.OrderSummaryDTO;
import com.alten.products.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<OrderSummaryDTO> getOrder() {
        return ResponseEntity.ok( orderService.getUserOrderDto());
    }

    @PostMapping("/add/{productId}")
    public ResponseEntity<String> addProductToOrder(@PathVariable Long productId) {
        orderService.addProductToOrder( productId);
        return ResponseEntity.ok("Produit ajouté à la liste d'envies");
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<String> removeProductFromOrder(@PathVariable Long productId) {

        orderService.removeProductFromOrder(productId);
        return ResponseEntity.ok("Produit supprimé de la liste d'envies");
    }

}
