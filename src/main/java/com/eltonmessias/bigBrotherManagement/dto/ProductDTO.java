package com.eltonmessias.bigBrotherManagement.dto;


import java.util.UUID;

public record ProductDTO(UUID id, String name, String description, double purchasePrice, double salePrice, int quantity, UUID category ) {
}
