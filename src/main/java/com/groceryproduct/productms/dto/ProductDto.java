package com.groceryproduct.productms.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Range;

public class ProductDto 
{
	@PositiveOrZero(message = "Enter a valid product Id")
	private long productId;
	@NotBlank(message = "Please enter a name")
	private String productName;
	@NotBlank(message = "Please enter a type of product")
	private String productType;
	@Positive(message = "Please add quantity")
	private int productQuantity;
	@NotNull(message = "Please add Price for the product")
	@Positive(message = "Please enter a valid amount")
	private double productPrice;
	@Range(min = 1,max = 5 , message = "Rating must be between  1 and 5")
	private double productRating;
	
	public ProductDto() {
		super();
	}

	public ProductDto(long productId, String productName, String productType, 
			int productQuantity, double productPrice, double productRating) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productType = productType;
		this.productQuantity = productQuantity;
		this.productPrice = productPrice;
		this.productRating = productRating;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public double getProductRating() {
		return productRating;
	}

	public void setProductRating(double productRating) {
		this.productRating = productRating;
	}
	
	
	
}
