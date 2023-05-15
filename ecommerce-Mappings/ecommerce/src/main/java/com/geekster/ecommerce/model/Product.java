package com.geekster.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @NotBlank
    private String productName;

    @NotNull(message = "Product price is required")
    @DecimalMin(value = "0.0", message = "Product price must be greater than or equal to 0.0")
    private Double productPrice;

    @NotBlank
    @Size(min = 10, max = 200)
    private String productDescription;

    @NotBlank
    private String productCategory;

    @NotBlank
    private String productBrand;

}
