package com.groceryproduct.productms.exception;

public class IdNotFoundException extends RuntimeException
{
	/**
	 * @author 
	 * This the custom exception produces when product specified is not present in 
	 * database
	 */
	
	private static final long serialVersionUID = 1L;

	public IdNotFoundException(String str) {
		super(str);
	 }
}
