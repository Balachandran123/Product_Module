package com.groceryproduct.productms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.groceryproduct.productms.entity.Product;
import com.groceryproduct.productms.response.entity.ErrorInfo;
import com.groceryproduct.productms.response.entity.SuccessInfo;
import com.groceryproduct.productms.service.ProductServiceImpl;
@SpringBootTest
class ProductmsApplicationTests 
{
	
	private  MockMvc mockMvc;
	@Autowired
	private WebApplicationContext context;
	
	ObjectMapper om = new ObjectMapper();
	
	@BeforeEach
	public  void setUp()
	{
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
		
	@Autowired
	ProductServiceImpl ps;
	
    //st - Success Test
	//ft - Failure Test
	
	//Test for Get Mapping	
	@Test
	void stGetProductById() throws Exception
	{
		/*
		 * JUnit Test Case 1
		 * 
		 * @param productId @return product 
		 * The test case will pass if the @param productId matches 
		 * with @return productId in Product Class
		 */
		mockMvc.perform(get("/products/id/{id}",5)
				.accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(5))
                .andReturn();

		Product p = ps.getProductById(5);
		assertEquals(5,p.getProductId());
	}
	
	@Test
	void ftGetProductById() throws Exception
	{
		MvcResult result = mockMvc.perform(get("/products/id/{id}",0)
				                  .accept(MediaType.APPLICATION_JSON))
	                              .andDo(print())
	                              .andExpect(status().isNotFound())
	                              .andReturn();
		String resultContent = result.getResponse().getContentAsString();
		//ErrorInfo info = om.readValue(resultContent, ErrorInfo.class);
		//assertEquals(404,info.getStausCode());
	}
	
	@Test
	void stGetProductByName() throws Exception
	{
		/*
		 * JUnit Test Case 2
		 * 
		 * @param productName @return product 
		 * The test case will pass if the @param productName matches 
		 * with @return productName in Product Class
		 */
		mockMvc.perform(get("/products/name/{name}","carrot")
				.accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
                
        List<Product> p = ps.getProductByName("carrot");
		assertTrue(p.stream().allMatch(obj->obj.getProductName()
				             .equals("carrot")));
	}
	
	@Test
	void ftGetProductbyName() throws Exception
	{
		MvcResult result = mockMvc.perform(get("/products/name/{name}","Black")
				                  .accept(MediaType.APPLICATION_JSON))
                                  .andDo(print()).andExpect(status().isNotFound())
                                  .andReturn();
        String resultContent = result.getResponse().getContentAsString();
        ErrorInfo info = om.readValue(resultContent, ErrorInfo.class);
        assertEquals(info.getStausCode(),404);	
	}
	
	@Test
	void stGetProductByType() throws Exception
	{
		mockMvc.perform(get("/products/type/{name}","biscuit")
				.accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
		List<Product> p = ps.getProductByType("biscuit");
		assertTrue(p.stream().allMatch(obj->obj.getProductType().equals("biscuit")));
	}
	
	@Test
	void ftGetProductByType() throws Exception
	{
		MvcResult result = mockMvc.perform(get("/products/type/{name}","Pizza").accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isNotFound()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        //ErrorInfo info = om.readValue(resultContent, ErrorInfo.class);
        //assertEquals(404,info.getStausCode());		
	}
	
	@Test
	void stInsertProduct() throws Exception
	{
		/*
		 * JUnit Test Case 3
		 * 
		 * @return Message
		 * The test case will pass if the new product is 
		 * successfully inserted
		 */
	
		Product p = new Product(99,"JUNIT","POST",1,900,5.5);
        String json = om.writeValueAsString(p);
		MvcResult result = mockMvc.perform(post("/products").content(json).contentType(MediaType.APPLICATION_JSON_VALUE))
				                  .andDo(print())
				                  .andExpect(status().isCreated())
				                  .andReturn();
		String resultContent = result.getResponse().getContentAsString();
        //SuccessInfo info = om.readValue(resultContent, SuccessInfo.class);
        //assertEquals(201,info.getStatusCode());	
	}
	
	@Test
	void ftInsertProduct() throws Exception
	{
		Product p = new Product(99,"JUNIT-5","POST",1,5.5,900);
        String json = om.writeValueAsString(p);
		MvcResult result = mockMvc.perform(post("/products").content(json).contentType(MediaType.APPLICATION_JSON_VALUE))
				                  .andDo(print())
				                  .andExpect(status().isBadRequest())
				                  .andReturn();
		String resultContent = result.getResponse().getContentAsString();
        //ErrorInfo info = om.readValue(resultContent, ErrorInfo.class);
        //assertEquals(400,info.getStausCode());		
	}
	
	@Test
	void stUpdateProduct() throws Exception
	{
		/*
		 * JUnit Test Case 4
		 * 
		 * @return Message 
		 * The test case will pass if the existing product
		 * is successfully updated
		 */
		Product p = new Product(99,"JUNIT","PUT",1,900,4.5);
        String json = om.writeValueAsString(p);
		MvcResult result = mockMvc.perform(put("/products/id/{id}",1).content(json).contentType(MediaType.APPLICATION_JSON_VALUE))
				                  .andDo(print())
				                  .andExpect(status().isOk())
				                  .andReturn();
		String resultContent = result.getResponse().getContentAsString();
        //SuccessInfo info = om.readValue(resultContent, SuccessInfo.class);
       // assertEquals(200,info.getStatusCode());	
		
	}
	
	@Test
	void ftUpdateProduct() throws Exception
	{
		Product p = new Product(99,"JUNIT-5","POST",1,900,9.5 );
        String json = om.writeValueAsString(p);
		MvcResult result = mockMvc.perform(put("/products/id/{id}",1).content(json).contentType(MediaType.APPLICATION_JSON_VALUE))
				                  .andDo(print())
				                  .andExpect(status().isBadRequest())
				                  .andReturn();
		String resultContent = result.getResponse().getContentAsString();
        //ErrorInfo info = om.readValue(resultContent, ErrorInfo.class);
        //assertEquals(400,info.getStausCode());
	}
	
	@Test
	void stDeleteProduct() throws Exception
	{
		/*
		 * JUnit Test Case 1
		 * 
         * @return Message 
		 * The test case will pass if the existing product
		 * is successfully deleted
		 */
		mockMvc.perform(delete("/products/name/{name}","JUNIT")
				.accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
	}
	
	@Test
	void ftDeleteProduct() throws Exception
	{
		MvcResult result = mockMvc.perform(delete("/products/id/{id}",99)
				.accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();
		String resultContent = result.getResponse().getContentAsString();
        //ErrorInfo info = om.readValue(resultContent, ErrorInfo.class);
       // assertEquals(404,info.getStausCode());
		
	}
}
