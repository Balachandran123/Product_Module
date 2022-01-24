package com.groceryproduct.productms.service;

import java.util.List;

import com.groceryproduct.productms.dto.ProductDto;
import com.groceryproduct.productms.entity.Product;


public interface IProductService 
{
	  List<Product> getAllProduct();
	  Product getProductById(int productId);
	  List<Product> getProductByName(String productName);
	  List<Product> getProductByType(String productType);
	  String insert(ProductDto pd);
	  String update(ProductDto pd);
	  String delete(int productId);
	  String deleteProducts(String name);
}
