package com.spring.jpa.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.spring.jpa.model.Product;
import com.spring.jpa.repository.ProductRepository;

/*
 * CREATE TABLE `products` (
  `prodId` int(11) NOT NULL,
  `prodName` varchar(45) NOT NULL,
  `prodPrice` decimal(11) NOT NULL,
  PRIMARY KEY (`prodId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

*/
//@Component
public class ProductDAO {

	public String name;
	public ProductDAO(@Value("sabari") String name) {
		this.name=name;
		System.out.println(name);
	}
	
	@Autowired
	ProductRepository productRepository;
	
	public List<Product> getProducts(){
		return productRepository.findAll();
	}
	
}
