package br.com.mercadolivre.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercadolivre.dto.RequestListProductDTO;
import br.com.mercadolivre.dto.RequestProductDTO;
import br.com.mercadolivre.model.Product;
import br.com.mercadolivre.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired 
	public ProductService service;
	
	@PostMapping("/insert-products-request")
	public void create(@RequestBody RequestListProductDTO products) throws IOException {
		
		List<Product> collect = products.getProducts().stream().map(e -> RequestProductDTO.productToDTO(e)).collect(Collectors.toList());
		service.create(collect);
		
	}
}
