package br.com.mercadolivre.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import br.com.mercadolivre.model.ProductPurchaseRequest;
import br.com.mercadolivre.model.ProductPurchaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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

		List<Product> collect = products.getProducts().stream().map(RequestProductDTO::dtoToProduct)
				.collect(Collectors.toList());
		service.create(collect);

		return new ResponseEntity<>(HttpStatus.CREATED);

	}

	@GetMapping("/products")
	public ResponseEntity<List<Product>> products(@RequestParam(required = false) String category) throws IOException {

		if (category != null) {
			List<Product> products = service.findByCategory(category);
			return ResponseEntity.ok(products);
		}

		List<Product> result = service.list();
		return ResponseEntity.ok(result);
	}

	@PostMapping("/purchase-request")
	public ResponseEntity<ProductPurchaseResponse> purchaseRequest(@RequestBody ProductPurchaseRequest products) throws Exception {
		return ResponseEntity.ok(service.compras(products));
	}

}
