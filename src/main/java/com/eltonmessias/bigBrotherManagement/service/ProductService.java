package com.eltonmessias.bigBrotherManagement.service;


import com.eltonmessias.bigBrotherManagement.dto.ProductDTO;
import com.eltonmessias.bigBrotherManagement.model.Category;
import com.eltonmessias.bigBrotherManagement.model.Product;
import com.eltonmessias.bigBrotherManagement.repository.CategoryRepository;
import com.eltonmessias.bigBrotherManagement.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;


    public Product convertToProduct(ProductDTO productDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        return product;
    }
    public ProductDTO convertToProductDTO(Product product) {
        ProductDTO productDTO;
        productDTO = new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPurchasePrice(), product.getSalePrice(), product.getQuantity(), product.getCategory().getId());
        return productDTO;
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public ProductDTO saveProduct(ProductDTO productDTO) {
        Category category = categoryRepository.findById(productDTO.category()).orElseThrow(() -> new RuntimeException("Category not found"));
        Product product = this.convertToProduct(productDTO);
        product.setCategory(category);
        productRepository.save(product);
        return this.convertToProductDTO(product);
    }

    public ProductDTO getProductById(UUID id) {
        Product product = this.productRepository.findById(id).orElse(null);
        assert product != null;
        return this.convertToProductDTO(product);
    }


    public ProductDTO updateProduct(UUID id, ProductDTO productDTO) throws Exception{
        Product product = this.productRepository.findById(id).orElseThrow(() -> new Exception("Product not found"));
        Category category = categoryRepository.findById(productDTO.category()).orElseThrow(() -> new Exception("Category not found"));
        if (product != null) {
            BeanUtils.copyProperties(productDTO, product, "id");
            Product updatedProduct = productRepository.save(product);
            updatedProduct.setCategory(category);
            return convertToProductDTO(updatedProduct);
        } else {
            throw new Exception("Product not found");
        }
    }

    public void deleteProduct(UUID id) {
        this.productRepository.deleteById(id);
    }



}
