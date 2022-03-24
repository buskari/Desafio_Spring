package br.com.mercadolivre.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Object> create(@RequestBody RequestListProductDTO products) throws IOException {

		List<Product> collect = products.getProducts().stream()
				.map(RequestProductDTO::productToDTO)
				.collect(Collectors.toList());
		service.create(collect);

		return new ResponseEntity<>(HttpStatus.CREATED);

	}
}
