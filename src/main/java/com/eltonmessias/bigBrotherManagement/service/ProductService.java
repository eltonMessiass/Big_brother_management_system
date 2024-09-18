package com.eltonmessias.bigBrotherManagement.service;


import com.eltonmessias.bigBrotherManagement.dto.ProductDTO;
import com.eltonmessias.bigBrotherManagement.model.Product;
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


    public Product convertToProduct(ProductDTO productDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        return product;
    }
    public ProductDTO convertToProductDTO(Product product) {
        ProductDTO productDTO;
        productDTO = new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPurchasePrice(), product.getSalePrice(), product.getQuantity());
        return productDTO;
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = this.convertToProduct(productDTO);
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
        if (product != null) {
            BeanUtils.copyProperties(productDTO, product, "id");
            Product updatedProduct = productRepository.save(product);
            return convertToProductDTO(updatedProduct);
        } else {
            throw new Exception("Product not found");
        }
    }

    public void deleteProduct(UUID id) {
        this.productRepository.deleteById(id);
    }



}
