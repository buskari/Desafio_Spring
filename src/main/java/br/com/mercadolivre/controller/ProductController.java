package br.com.mercadolivre.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercadolivre.model.Product;
import br.com.mercadolivre.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired 
	public ProductService service;
	
	@PostMapping
	public void create(List<Product> products) {
		service.create(products);
	}
}
