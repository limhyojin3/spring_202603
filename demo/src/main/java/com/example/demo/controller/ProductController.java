package com.example.demo.controller;

import com.example.demo.dao.ProductService;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ProductController {
	
	//오토와이어 까먹지말기!**
	@Autowired
	ProductService productService;

	
	ProductController(ProductService productService) {
		this.productService = productService;
	}

	@RequestMapping("/product/chart.do") 
	public String test(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
		return "/product/chart";
	}
	
	//order
	@RequestMapping("/product/order.do") 
	public String order(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
		return "/product/order-chart";
	}
	
	///product/order.dox <=주소확인 잘하기! **
	@RequestMapping(value = "/product/order.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String order(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();//공간생성
		
		resultMap = productService.getOrderList(map);
		
		return new Gson().toJson(resultMap); 
	}
	
	@RequestMapping("/product/payment.do") 
	public String payment(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
		return "/product/payment";
	}
	
	@RequestMapping("/product/auth.do") 
	public String auth(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
		return "/product/auth";
	}
}
