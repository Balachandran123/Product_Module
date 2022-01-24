package com.groceryproduct.productms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



@ApiModel("This is product table which customer can choose to order")
@Entity
public class Product 
{
	@ApiModelProperty("Each product should consist unique Product Id")
	@Id
	@GeneratedValue
	private long productId;
	@ApiModelProperty("This is the product name")
	private String productName;
	@ApiModelProperty("The product comes under this category")
	private String productType;
	@ApiModelProperty("Number of Quantities Ordered")
	private int productQuantity;
	@ApiModelProperty("This is the price of the product")
	private double productPrice;
	@ApiModelProperty("This is the rating of the product")
	private double productRating;
	
	
	public Product() {
		super();
	}


	public Product(long productId, String productName, String productType,  int productQuantity,
			double productPrice, double productRating) {
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


	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productType=" + productType
				+ ", productQuantity=" + productQuantity + ", productPrice=" + productPrice + ", productRating="
				+ productRating + "]";
	}


	
	
	
}
