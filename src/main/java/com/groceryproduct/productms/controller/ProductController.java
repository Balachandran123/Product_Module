package com.groceryproduct.productms.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.groceryproduct.productms.dto.ProductDto;
import com.groceryproduct.productms.entity.Product;
import com.groceryproduct.productms.response.entity.SuccessInfo;
import com.groceryproduct.productms.service.ProductServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Validated
public class ProductController 
{
	@Autowired
	ProductServiceImpl service;
	Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	//Customer and Admin Accessible
	@GetMapping(value="/products/all",produces = "application/json")
	public List<Product> getAllProduct()
	{
		logger.trace("Get all product method is accessed");
		return service.getAllProduct();
	}
	@GetMapping(value="/products/byid/{id}",produces = "application/json")
	public Product getProductById(@PathVariable("id") int productId)
	{
		logger.trace("Get product by Id method is accessed");
		return service.getProductById(productId);
	}
	@GetMapping(value="/products/byname/{name}",produces = "application/json")
	public List<Product> getProductByName(@PathVariable("name") String productName)
	{
		logger.trace("Get product by name method is accessed");
		return service.getProductByName(productName);
	}
	@GetMapping(value="/products/bytype/{name}",produces = "application/json")
	public List<Product> getProductByType(@PathVariable("name") String productType)
	{
		logger.trace("Get product by type method is accessed");
		return service.getProductByType(productType);
	}
	
	//Only Admin Accessible
	@PostMapping(value="/products/add",produces = "application/json")
    public ResponseEntity<SuccessInfo> insertProduct(@Valid @RequestBody ProductDto pd)
	{
		String str = service.insert(pd);
		logger.trace("add product method is accessed");
		return new ResponseEntity<>(new SuccessInfo(HttpStatus.CREATED, 201, str),HttpStatus.CREATED);
		
    }
	@PutMapping(value="/products/update/{id}")
	public ResponseEntity<SuccessInfo> updateProduct(@PathVariable("id") int productId, @Valid @RequestBody ProductDto pd)
	{
		String str = service.update(pd);
		logger.trace("update product by Id method is accessed");
		return new ResponseEntity<>(new SuccessInfo(HttpStatus.OK, 200, str),HttpStatus.OK);
		
	}
	@DeleteMapping(value="/products/delete/{id}")
	public String deleteProduct(@PathVariable("id") int productId)
	{
		logger.trace("delete product by Id method is accessed");
		return service.delete(productId);
	}
	
	@ApiOperation("This operation made for unit testing")
	@DeleteMapping(value="/products/deleteall/{name}")
	public String deleteAllProduct(@PathVariable("name") String productName)
	{
		logger.trace("Delete all product by name method is accessed");
		return service.deleteProducts(productName);
	}
}
