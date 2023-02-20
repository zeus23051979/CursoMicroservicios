package com.academy.digitallab.product.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.academy.digitallab.product.models.entity.Category;
import com.academy.digitallab.product.models.entity.Product;
import com.academy.digitallab.product.models.service.IProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	@Qualifier("productService")
	private IProductService productService;
	
	@GetMapping
	public ResponseEntity<List<Product>> listProduct(@RequestParam(required=false)Long categoryId){
		
		List<Product> products= new ArrayList<Product>();
		if(categoryId==null) {
			products= productService.listAllProduct();
			if(products.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			
		}else {
			
			//Entity entity = Entity.builder().setName(Name).setNumber(Number).build();
            
			
			products = productService.findByCategory(Category.builder().id(categoryId).build());
			if(products.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
		}		
		
		return ResponseEntity.ok(products);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable Long id){
		Product product = productService.getProduct(id);
		if(product == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(product);
	}
	
	
	@PostMapping
	public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product, BindingResult result){
		if(result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, result.getFieldError().toString());
		}
		Product productCreate= productService.createProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(productCreate);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long id){
		product.setId(id);
		Product productDB= productService.createProduct(product);
		
		if(productDB==null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(productDB);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable Long id){
		Product productDelete= productService.deleteProduct(id);
		
		if(productDelete==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(productDelete);
	}
	
	
	@GetMapping("/{id}/stock")
	public ResponseEntity<Product> updateStockProduct(@PathVariable Long id, @RequestParam(name="quantity", required=true) Double quantity){
		Product product = productService.updateStock(id, quantity);
		
		if(product==null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(product);
	}


//	private String formatMessage( BindingResult result){
//        List<Map<String,String>> errors = result.getFieldErrors().stream()
//                .map(err ->{
//                    Map<String,String>  error =  new HashMap<>();
//                    error.put(err.getField(), err.getDefaultMessage());
//                    return error;
//
//                }).collect(Collectors.toList());
//        ErrorMessage errorMessage = ErrorMessage.builder()
//                .code("01")
//                .messages(errors).build();
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString="";
//        try {
//            jsonString = mapper.writeValueAsString(errorMessage);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        return jsonString;
//    }
}
