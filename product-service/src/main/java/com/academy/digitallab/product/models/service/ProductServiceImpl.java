package com.academy.digitallab.product.models.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academy.digitallab.product.models.entity.Category;
import com.academy.digitallab.product.models.entity.Product;
import com.academy.digitallab.product.models.repository.IProductDao;

@Service("productService")
public class ProductServiceImpl implements IProductService {

	@Autowired
	@Qualifier("productRepository")
	private IProductDao productRepository;

	@Transactional(readOnly = true)
	public List<Product> listAllProduct() {
		return productRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Product getProduct(Long id) {
		return productRepository.findById(id).orElse(null);
	}

	@Transactional
	public Product createProduct(Product product) {
		product.setStatus("CREATED");
		product.setCreateAt(new Date());
		return productRepository.save(product);
	}

	@Transactional
	public Product updateProduct(Product product) {
		Product productDB = getProduct(product.getId());
		if (null == productDB) {
			return null;
		}
		productDB.setName(product.getName());
		productDB.setDescription(product.getDescription());
		productDB.setCategory(product.getCategory());
		productDB.setPrice(product.getPrice());

		return productRepository.save(productDB);
	}

	@Transactional
	public Product deleteProduct(Long id) {
		Product productDB = getProduct(id);
		if (null == productDB) {
			return null;
		}

		productDB.setStatus("DELETED");
		return productRepository.save(productDB);

	}

	@Transactional(readOnly = true)
	public List<Product> findByCategory(Category category) {
		return productRepository.findByCategory(category);
	}

	@Transactional
	public Product updateStock(Long id, Double quantity) {
		Product productDB = getProduct(id);
		if (null == productDB) {
			return null;
		}

		Double stock = productDB.getStock() + quantity;
		productDB.setStock(stock);

		return productRepository.save(productDB);
	}

}

