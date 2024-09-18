package com.eltonmessias.bigBrotherManagement.service;

import com.eltonmessias.bigBrotherManagement.dto.CategoryDTO;
import com.eltonmessias.bigBrotherManagement.model.Category;
import com.eltonmessias.bigBrotherManagement.repository.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryDTO convertToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO(category.getId(), category.getName(), category.getDescription());
        BeanUtils.copyProperties(category, categoryDTO);
        return categoryDTO;
    }

    public Category convertToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        return category;
    }

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        Category savedCategory = categoryRepository.save(category);
        BeanUtils.copyProperties(savedCategory, categoryDTO);
        return categoryDTO;
    }

    public CategoryDTO updateCategory(UUID id, CategoryDTO categoryDTO) throws Exception {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new Exception("Category not found"));
        if (category != null) {
//            BeanUtils.copyProperties(categoryDTO, category);
            category.setName(categoryDTO.name());
            category.setDescription(categoryDTO.description());
            Category savedCategory = categoryRepository.save(category);
//            BeanUtils.copyProperties(savedCategory, categoryDTO);
            return convertToDTO(savedCategory);
        } else {
            throw new Exception("Product not found");
        }
    }
}