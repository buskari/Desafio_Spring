package br.com.mercadolivre.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import br.com.mercadolivre.dto.ResponseProductDTO;
import br.com.mercadolivre.model.ProductPurchaseRequest;
import br.com.mercadolivre.model.ProductPurchaseResponse;
import br.com.mercadolivre.service.ProductValidatePrice;
import br.com.mercadolivre.service.ProductValidateQuantity;
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
	private List<ResponseProductDTO> responseProductDTOList;

	@PostMapping("/insert-products-request")
	public ResponseEntity<List<ResponseProductDTO>> create(@RequestBody RequestListProductDTO products) throws IOException {
			List<Product> collect = products
					.getProducts()
					.stream()
					.map(RequestProductDTO::dtoToProduct)
					.collect(Collectors.toList());

			service.create(collect, Arrays.asList(
					new ProductValidatePrice(collect),
					new ProductValidateQuantity(collect)
			));
			responseProductDTOList = ResponseProductDTO.convertToDTO(collect);
			return new ResponseEntity<>(responseProductDTOList, HttpStatus.CREATED);
	}

	@GetMapping("/products")
	public ResponseEntity<List<ResponseProductDTO>> products(
			@RequestParam(required = false) String category,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String brand,
			@RequestParam(required = false) BigDecimal price,
			@RequestParam(required = false) Integer quantity,
			@RequestParam(required = false) String freeShipping,
			@RequestParam(required = false) String prestige,
			@RequestParam(required = false) Integer order
	) throws IOException {
		List<Product> result = service.findProducts(
				category,
				name,
				brand,
				price,
				quantity,
				freeShipping,
				prestige,
				order
		);

		responseProductDTOList = ResponseProductDTO.convertToDTO(result);
		return ResponseEntity.ok(responseProductDTOList);
	}

	@PostMapping("/purchase-request")
	public ResponseEntity<ProductPurchaseResponse> purchaseRequest(
			@RequestBody ProductPurchaseRequest products
	) throws Exception {
		return ResponseEntity.ok(service.compras(products));
	}
}
