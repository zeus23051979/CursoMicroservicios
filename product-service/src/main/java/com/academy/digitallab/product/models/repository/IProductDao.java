package com.academy.digitallab.product.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.academy.digitallab.product.models.entity.Category;
import com.academy.digitallab.product.models.entity.Product;

@Repository("productRepository")
public interface IProductDao extends JpaRepository<Product, Long> {
	
	public List<Product> findByCategory(Category category);

}

