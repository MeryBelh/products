package com.alten.products.service.impl;

import com.alten.products.exception.ResourceNotFoundException;
import com.alten.products.model.Product;
import com.alten.products.repository.ProductRepository;
import com.alten.products.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    public Product createProduct(Product product) {
        if (product.getCreatedAt() == null) {
            product.setCreatedAt(Instant.now().toEpochMilli());
        }
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        product.setName(Objects.requireNonNullElse(productDetails.getName(), product.getName()));
        product.setPrice(Objects.requireNonNullElse(productDetails.getPrice(), product.getPrice()));
        product.setQuantity(Objects.requireNonNullElse(productDetails.getQuantity(), product.getQuantity()));
        product.setUpdatedAt(Instant.now().toEpochMilli());
        return productRepository.save(product);
    }

    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
