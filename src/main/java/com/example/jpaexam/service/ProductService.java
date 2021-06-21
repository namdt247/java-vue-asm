package com.example.jpaexam.service;

import com.example.jpaexam.entity.Product;
import com.example.jpaexam.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

@Service
@Configurable
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Boolean create(Product product) {
        try {
            productRepository.save(product);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private Iterable<Product> list() {
        return productRepository.findAll();
    }
}
