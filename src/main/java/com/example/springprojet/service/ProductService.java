package com.example.springprojet.service;

import com.example.springprojet.model.Product;
import com.example.springprojet.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public List<Product> getAllProduct(){return productRepository.findAll();}
    public void addproduct(Product product){
        productRepository.save(product);
    }
    public void removeproduct(long id){
        productRepository.deleteById(id);
    }
    public Optional<Product> getproductbyid(long id){
        return productRepository.findById(id);
    }
    public List<Product> getallPBC(int id){
        return productRepository.findByCategoryId(id);
    }




}
