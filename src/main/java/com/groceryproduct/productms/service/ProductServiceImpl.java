package com.groceryproduct.productms.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.groceryproduct.productms.dto.ProductDto;
import com.groceryproduct.productms.entity.Product;
import com.groceryproduct.productms.exception.IdNotFoundException;
import com.groceryproduct.productms.mapper.ProductMappers;
import com.groceryproduct.productms.repository.IProductRepository;

@Service("ps")
public class ProductServiceImpl implements IProductService
{
	private static final String EXP_MESSAGE = "Sorry, Your product is not available";

	
	@Autowired
	IProductRepository pr;

	@Override
	public List<Product> getAllProduct() 
	{		
		return pr.findAll();
	}

	@Override
	public Product getProductById(int productId) 
	{		
		Optional<Product> p = pr.findById((long) productId);
		if(p.isPresent())
			return p.get();
		else
			throw new IdNotFoundException(EXP_MESSAGE);
	}

	@Override
	public List<Product> getProductByName(String productName) 
	{		
		List<Product> p = pr.findByproductName(productName);
		if(p.isEmpty())
			throw new IdNotFoundException(EXP_MESSAGE);
		else
			return p;
	}

	@Override
	public List<Product> getProductByType(String productType) {
		List<Product> p = pr.findByproductType(productType);
		if(p.isEmpty())
			throw new IdNotFoundException(EXP_MESSAGE);
		else
			return p;
			
	}

	@Override
	public String insert(ProductDto pd) 
	{
		Product p = ProductMappers.dtotToEntity(pd);
		pr.save(p);
		return pd.getProductName()+" has added successfully";
	}

	@Override
	public String update(ProductDto pd) 
	{
		Product p = ProductMappers.dtotToEntity(pd);
		if(p.toString().isEmpty())
		{
			throw new IdNotFoundException(EXP_MESSAGE);
		}
		else
		{
			Product pro = pr.save(p);
			return pro.getProductName()+" has updated successfully";
		}
		
     }

	@Override
	public String delete(int productId) 
	{
		if(pr.existsById((long) productId))
		{
			pr.deleteById((long) productId);
			return "Product Id "+productId+" has deleted successfully";
		}
		else
		{		
			throw new IdNotFoundException(EXP_MESSAGE);
		}
	}
	
	//To Commit after Testing
		
		@Override
		public String deleteProducts(String name) 
		{
			
			pr.deleteByName(name);
			return "Products with name  "+name+" is deleted Successfully !!!";
		}

			
}
