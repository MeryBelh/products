package com.alten.products.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the product", example = "1", required = false)
    private Long id;

    @NotBlank(message = "Product code is required")
    @Size(max = 50, message = "Product code must be at most 50 characters")
    @Schema(description = "Product code", example = "P001", required = true)
    private String code;

    @NotBlank(message = "Product name is required")
    @Size(max = 255, message = "Product name must be at most 255 characters")
    @Schema(description = "Product name", example = "Laptop", required = true)
    private String name;

    @NotBlank(message = "Product description is required")
    @Size(max = 1000, message = "Product description must be at most 1000 characters")
    @Schema(description = "Product description", example = "A high-performance laptop", required = true)
    private String description;

    @NotBlank(message = "Product image is required")
    @Size(max = 255, message = "Product image file name must be at most 255 characters")
    @Schema(description = "Product image file name", example = "laptop.jpg", required = true)
    private String image;

    @NotBlank(message = "Product category is required")
    @Size(max = 100, message = "Product category must be at most 100 characters")
    @Schema(description = "Product category", example = "Electronics", required = true)
    private String category;

    @NotNull(message = "Product price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    @Schema(description = "Product price", example = "1200.50", required = true)
    private Double price;

    @NotNull(message = "Product quantity is required")
    @Min(value = 0, message = "Quantity must be at least 0")
    @Schema(description = "Product quantity in stock", example = "10", required = true)
    private Integer quantity;

    @NotBlank(message = "Internal reference code is required")
    @Size(max = 100, message = "Internal reference code must be at most 100 characters")
    @Schema(description = "Internal reference code", example = "LAP12345", required = true)
    private String internalReference;

    @NotNull(message = "Shell ID is required")
    @Schema(description = "Shell ID associated with the product", example = "101", required = true)
    private Long shellId;

    @Enumerated(EnumType.STRING)
    @Column(name = "inventory_status")
    @NotNull(message = "Inventory status is required")
    @Schema(description = "Inventory status of the product", example = "INSTOCK", required = true)
    private InventoryStatus inventoryStatus;

    @Min(value = 0, message = "Rating must be at least 0")
    @Max(value = 5, message = "Rating must be at most 5")
    @Schema(description = "Product rating (0 to 5)", example = "4.5", required = false)
    private Double rating;

    @Schema(description = "Timestamp when the product was created", example = "1696000000000", required = false)
    private Long createdAt;

    @Schema(description = "Timestamp when the product was last updated", example = "1696000000000", required = false)
    private Long updatedAt;
}
