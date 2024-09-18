package com.eltonmessias.bigBrotherManagement.model;

import com.eltonmessias.bigBrotherManagement.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String description;
    private double purchasePrice;
    private double salePrice;
    private int quantity;

//    public Product(ProductDTO data) {
//        this.name = data.name();
//        this.description = data.description();
//        this.purchasePrice = data.purchasePrice();
//        this.salePrice = data.salePrice();
//        this.quantity = data.quantity();
//    }
}
