package com.alten.products.model.dto;

import com.alten.products.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishlistSummaryDTO {
    private Long id;
    private List<Product> products;
}
