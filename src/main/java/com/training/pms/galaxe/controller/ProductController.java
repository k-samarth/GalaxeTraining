package com.training.pms.galaxe.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.pms.galaxe.dao.ProductDAO;
import com.training.pms.galaxe.model.Product;
import com.training.pms.galaxe.service.ProductService;


@RestController
@RequestMapping("product")
public class ProductController {

	@Autowired
	ProductService productService;
	
	public ProductController() {
		// TODO Auto-generated constructor stub
	}
	
	@PostMapping()					//http://localhost:9090/product/		-POST		-BODY (product) 102
	public ResponseEntity<String> saveProduct(@RequestBody Product product) {
		ResponseEntity<String> responseEntity;
		int pId = product.getProductId();
		if(productService.isProductExists(pId)) {
			responseEntity = new ResponseEntity<String>("Product with product id :"+pId+" already exists", HttpStatus.CONFLICT);
		}
		else
		{
			String message = productService.saveProduct(product);
			responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
		}
		return responseEntity;
	}
	Optional<Product> product;
	@GetMapping("{productId}") // localhost:9090/product/5
    public ResponseEntity<Product> getProduct(@PathVariable("productId") Integer productId) {
        ResponseEntity<Product> responseEntity;
        if (productService.isProductExists(productId)) {
            product = productService.getProduct(productId);
            responseEntity = new ResponseEntity<Product>(HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
        }
        return responseEntity;
    }
	
	@GetMapping					//http://localhost:9090/product
	public ResponseEntity<List<Product>> getProducts() {
		List<Product> products = productService.getProduct();
				
		ResponseEntity<List<Product>> responseEntity;

		if(products.isEmpty()) {
			responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.NO_CONTENT);
		}
		else
		{
			responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		}
		return responseEntity;
	}
	
//	@GetMapping
//	public List<Product> getProducts() {
//		return (List<Product>) productDAO.findAll();
//	}
//	
//	@GetMapping("{productId}")
//	public Optional<Product> getProduct(@PathVariable("productId")Integer productId) {
//		return productDAO.findById(productId);
//	}
//	
//	@DeleteMapping("{productId}")
//	public String deleteProduct(@PathVariable("productId")Integer productId) {
//		productDAO.deleteById(productId);
//		return "Deleting a single product with product Id: "+productId;
//	}
	
	@PutMapping()
	public ResponseEntity<String> updateProduct(@RequestBody Product product)
	{
		ResponseEntity<String> responseEntity;
		int pId = product.getProductId();
		if(!productService.isProductExists(pId)) {
			responseEntity = new ResponseEntity<String>("Product with product id :"+pId+" doesn't exists", HttpStatus.NOT_ACCEPTABLE);
		}
		else
		{
			String message = productService.updateProduct(product);
			responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@GetMapping("searchByProductName/{productName}") // url - localhost:9090/product/2435678
	public ResponseEntity<List<Product>> getProductByName(@PathVariable("productName") String productName) {
	
		ResponseEntity<List<Product>> responseEntity;
		List<Product> products  = productService.searchProduct(productName);
		if(products.isEmpty()) {
			responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.NO_CONTENT);
		}
		else
		{
			responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@GetMapping("searchByRange/{min}/{max}") // url - localhost:9090/product/2435678
	public ResponseEntity<List<Product>> getProductByRange(@PathVariable("min") Integer min, @PathVariable("max") Integer max) {
	
		ResponseEntity<List<Product>> responseEntity;
		List<Product> products  = productService.searchProduct(min,max);
		if(products.isEmpty()) {
			responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.NO_CONTENT);
		}
		else
		{
			responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		}
		return responseEntity;
	}
	
}
