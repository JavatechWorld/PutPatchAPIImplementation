package com.spring.implementation.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.implementation.model.Product;
import com.spring.implementation.repositories.ProductRepository;

@RestController
public class ProductRestController {
	
	@Autowired
	ProductRepository productRepository;

	@PostMapping("/save")
	public Product saveProduct(@RequestBody Product product) {
		return productRepository.save(product);
	}
	
	@GetMapping("/getAllData")
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	@PatchMapping("/updateQuantity/{id}")
	public Product updateQuantity(@PathVariable int id, @RequestParam int qunatity) throws IllegalAccessException {
		Optional<Product> product = productRepository.findById(id);
		if(product.isPresent()) {
			Product existingProduct = product.get();
			existingProduct.setQuantityInStock(qunatity);
			return productRepository.save(existingProduct);
		}else {
			throw new IllegalAccessException("for the Id given, data is not present in database");
		}
	}
	
	@PutMapping("/update/{id}")
	public Product updateProduct(@PathVariable int id, @RequestBody Product product) throws IllegalAccessException {
		Optional<Product> existingproduct = productRepository.findById(id);
		if(existingproduct.isPresent()) {
			Product newProduct = existingproduct.get();
			newProduct.setDescription(product.getDescription());
			newProduct.setName(product.getName());
			newProduct.setPrice(product.getPrice());
			newProduct.setQuantityInStock(product.getQuantityInStock());
			return productRepository.save(newProduct);
		}else {
			throw new IllegalAccessException("for the Id given, data is not present in database");
		}
	}
	
}
