package com.amazon.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.amazon.app.beans.Product;
import com.amazon.app.exceptions.ProductNotFoundException;

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
	JdbcTemplate jdbcTemplate;//Spring JDBC 
	
	
	static List<Product> products;
	
	static {
		products = new ArrayList<Product>();
		Product p1 = new Product(101,"IPad",344.45f);
		Product p2 = new Product(102,"RaspberryPI",654.2f);
		Product p3 = new Product(103,"Wallet",12.32f);
		Product p4 = new Product(104,"Laptop",4564.45f);
		Product p5 = new Product(105,"SmartWatch",234.56f);
		
		products.add(p1);
		products.add(p2);
		products.add(p3);
		products.add(p4);
		products.add(p5);
	}
	
	public List<Product> getProducts(){
		List<Product> products = jdbcTemplate.query("select * from products", new RowMapper<Product>() {

			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				Product p = new Product();
				p.setProdId(rs.getInt("prodId"));
				p.setProdName(rs.getString("prodName"));
				p.setProdPrice(rs.getFloat("prodPrice"));
				return p;
			}
			
		});
		return products;
	}
	
	public List<Product> getProduct(String productName) throws Exception {

		List <Product> productInfo = products.stream()
				.filter((p) -> p.getProdName().equals(productName))
				.collect(Collectors.toList());
	
		if(productInfo.isEmpty()) {
			//throw new ExceptionException("Sorry the given Product Name not Available"); // we should have give ProductNotFoundException which we need to create our own exception
			throw new ProductNotFoundException("Sorry the given Product Name not Available"); // This can be handled controller advice or controller too
		}
		else {
			return productInfo;
		}
	}
	
	public boolean addProduct(Product product) {
		int rows = jdbcTemplate.update("insert into products values(?,?,?)",
		product.getProdId(),product.getProdName(),product.getProdPrice());
		//query can be avoided with ORM(hibernate is one of ORM)
		//Traditionally we go via raw JDBC tamplate. But here we abstract everything into the Jdbc template(in spring jdbc)
		if(rows > 0) {
			return true;
		}
		
		return false;
	}
	
}
