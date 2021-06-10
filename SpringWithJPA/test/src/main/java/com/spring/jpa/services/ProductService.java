package com.spring.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.jpa.dao.ProductDAO;
import com.spring.jpa.model.Product;

@Component
public class ProductService {

	@Autowired
	//@Qualifier("p2")
	ProductDAO productDAO ;

	/*public ProductService(ProductDAO productDAO) {		
		this.productDAO = productDAO;
	}*/
	
	public List<Product> getAllProducts(){
		//System.out.println(productDAO.name);
		return productDAO.getProducts();
	}
	
}
