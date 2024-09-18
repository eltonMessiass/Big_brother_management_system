package com.eltonmessias.bigBrotherManagement.service;

import com.eltonmessias.bigBrotherManagement.dto.CategoryDTO;
import com.eltonmessias.bigBrotherManagement.model.Category;
import com.eltonmessias.bigBrotherManagement.repository.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        Category savedCategory = categoryRepository.save(category);
        BeanUtils.copyProperties(savedCategory, categoryDTO);
        return categoryDTO;
    }
}
