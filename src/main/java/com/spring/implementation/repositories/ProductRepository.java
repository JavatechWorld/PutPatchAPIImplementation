package com.spring.implementation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.implementation.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
