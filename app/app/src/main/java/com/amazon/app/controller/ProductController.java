package com.amazon.app.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

//Back Controller
@Controller
@RequestMapping("/product") //If URI's are unique you dont need identifier. this is just example identifier
public class ProductController {

	// If URI's are unique you dont need identifier
	@ResponseBody// to append this with response header
	/*@RequestMapping(value = "/getproduct", method=RequestMethod.GET) //by default GET
*/	
	@GetMapping(value = "/getproduct/{id}"/*, consumes = "text/plain"*/, produces = MediaType.APPLICATION_JSON_VALUE) // same way we have postmapping and rest. latest usage
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
}
