package com.eltonmessias.bigBrotherManagement.repository;

import com.eltonmessias.bigBrotherManagement.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
