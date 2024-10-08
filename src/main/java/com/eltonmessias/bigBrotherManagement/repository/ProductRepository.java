package com.eltonmessias.bigBrotherManagement.repository;

import com.eltonmessias.bigBrotherManagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
