package com.spring.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.jpa.model.Product;
import com.spring.jpa.services.ProductService;

//Back Controller
@Controller
@RequestMapping("/product") //If URI's are unique you dont need identifier. this is just example identifier
public class ProductController {

	@Autowired
	ProductService productService;
	
	// If URI's are unique you dont need identifier
	@ResponseBody// to append this with response header
	/*@RequestMapping(value = "/getproduct", method=RequestMethod.GET) //by default GET
*/	
	@GetMapping(value = "/getproduct/{id}"/*, consumes = "text/plain"*/, produces = "application/json") // same way we have postmapping and rest. latest usage
	public String getMessage(@PathVariable String id) {
		return "Welcome :  " + id;
	}
	
	@ResponseBody
	@GetMapping("/getproduct1") // same way we have postmapping and rest. latest usage
	public ModelAndView getMessage1() {
		ModelAndView mv = new ModelAndView("Welcome");
		mv.addObject("message1", "MESSAGE1");
		mv.addObject("message2", "MESSAGE2");
		return mv;
	}
	
	@GetMapping("/getException") // same way we have postmapping and rest. latest usage
	public ModelAndView getException() throws Exception {
		throw new Exception("This is Expected!!! ");
	}
	
	
	@GetMapping("/getAllProducts")
	@ResponseBody
	public List<Product> getProductDetails() {
		return productService.getAllProducts();
	}
	
	/*
	 * @GetMapping("/product/{productName}")//URI templating name - both path and
	 * url template is for diff purpose
	 * 
	 * @ResponseBody public List<Product> getProductDetails(@PathVariable String
	 * productName) throws Exception {// if you want diff name give inside
	 * brackets @pathvariable ( "samplename" String productname) return
	 * productService.getAllProductsByName(productName); }
	 * 
	 * 
	 * @PostMapping("/insertproduct")//URI templating name - both path and url
	 * template is for diff purpose
	 * 
	 * @ResponseBody //Body type should be Application/Json in rest client
	 * //@RequestBody - to read request envelope which will have request header
	 * public boolean insertProduct(@RequestBody Product product) {// if you want
	 * diff name give inside brackets @pathvariable ( "samplename" String
	 * productname) return productService.addProduct(product); }
	 */
	
	
}
