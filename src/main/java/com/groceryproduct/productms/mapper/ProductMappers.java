package com.groceryproduct.productms.mapper;

import com.groceryproduct.productms.dto.ProductDto;
import com.groceryproduct.productms.entity.Product;

public class ProductMappers 
{
 public static Product dtotToEntity(ProductDto pd)
 {
	 
	 Product p = new Product(pd.getProductId(),pd.getProductName(),pd.getProductType(),pd.getProductQuantity(),pd.getProductPrice(),pd.getProductRating());
	 return p;
 }
}
