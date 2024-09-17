package com.eltonmessias.bigBrotherManagement.service;


import com.eltonmessias.bigBrotherManagement.dto.ProductDTO;
import com.eltonmessias.bigBrotherManagement.model.Product;
import com.eltonmessias.bigBrotherManagement.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public ProductDTO convertToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPurchasePrice(), product.getSalePrice(), product.getQuantity());
        BeanUtils.copyProperties(product, productDTO);
        return productDTO;
    }

    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.name());
        product.setDescription(productDTO.description());
        product.setPurchasePrice(productDTO.purchasePrice());
        product.setSalePrice(productDTO.salePrice());
        product.setQuantity(productDTO.quantity());

        Product savedProduct = productRepository.save(product);


        return new ProductDTO(
                savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getDescription(),
                savedProduct.getPurchasePrice(),
                savedProduct.getSalePrice(),
                savedProduct.getQuantity()
        );

    }
}
