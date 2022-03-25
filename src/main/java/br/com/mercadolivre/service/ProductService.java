package br.com.mercadolivre.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.mercadolivre.dto.ProductPurchasedRequestDTO;
import br.com.mercadolivre.dto.ProductPurchasedResponseDTO;
import br.com.mercadolivre.model.ProductPurchaseRequest;
import br.com.mercadolivre.model.ProductPurchaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.mercadolivre.model.Product;
import br.com.mercadolivre.util.FileUtils;

@Service
public class ProductService {

	private static final String JSON_PRODUCTS_PATH = "./products.json";

	@Autowired
	private FileService<Product> fileService;

	private ObjectMapper mapper = new ObjectMapper();

	public void create(List<Product> products) throws IOException {
		FileUtils<Product> fileUtils = new FileUtils<>();
		List<Product> list = fileUtils.readObjectsFromFile(JSON_PRODUCTS_PATH);
		list.addAll(products);
		fileUtils.writeObjectToFile(list, JSON_PRODUCTS_PATH);
	}

	public List<Product> findByCategory(String category) throws IOException {

		List<Product> fileProducts = fileService.findAll(JSON_PRODUCTS_PATH);

		List<Product> pojos = mapper.convertValue(fileProducts, new TypeReference<List<Product>>() {
		});

		return pojos.stream().filter(product -> product.getCategory().equals(category))
			.collect(Collectors.toList());

	}

	public List<Product> list() throws IOException {
		FileUtils<Product> fileUtils = new FileUtils<>();
		List<Product> list = fileUtils.readObjectsFromFile(JSON_PRODUCTS_PATH);
		List<Product> pojos = mapper.convertValue(list, new TypeReference<List<Product>>() {
		});
		return pojos;
	}

	public List<Product> listProductsCatEName(List<Product> products, String categoria, String name) { // filtra por categoria e nome
		return products.stream().filter(a -> a.getCategory().equalsIgnoreCase(categoria))
				.filter(a -> a.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
	}

	public List<Product> listProductsCatAndBrand(List<Product> products, String category, String brand) { //filtra por categoria e brand
		return products.stream().filter(a -> a.getCategory().equalsIgnoreCase(category))
				.filter(a -> a.getBrand().equalsIgnoreCase(brand)).collect(Collectors.toList());
	}

	public List<Product> listProductsFreeShippingAndPrestige(List<Product> products, String freeShipping, String prestige) { //filtra por freeShipping e Prestige
		return products.stream().filter(a -> a.getFreeShipping().toString().equals(freeShipping))
				.filter(a -> a.getPrestige().equals(prestige)).collect(Collectors.toList());
	}

	public List<Product> listProductsCatAndFreeShipping(List<Product> products, String category, String freeShipping) { //filtra por categoria e freeShipping
		return products.stream().filter(a -> a.getCategory().equalsIgnoreCase(category))
				.filter(a -> a.getFreeShipping().toString().equals(freeShipping)).collect(Collectors.toList());
	}

	public ProductPurchaseResponse compras(ProductPurchaseRequest products) throws Exception {
		List<Product> saida = new ArrayList<>();
		BigDecimal total = BigDecimal.valueOf(0);
		List<Product> estoque = list();
		ProductPurchasedResponseDTO productPurchesedDTO = new ProductPurchasedResponseDTO();
		ProductPurchaseResponse productPurchaseResponse = new ProductPurchaseResponse();

		for (ProductPurchasedRequestDTO productCompra : products.getProdutcsPurchesed()) {
			Optional<Product> opt = findById(productCompra.getProductId());
			if (opt.isEmpty()){
				throw new Exception("Produto inexistente");
			}
			for (Product productEstoque : estoque) {
				if (productCompra.getProductId().equals(productEstoque.getProductId())) {
					if (productCompra.getQuantity() > productEstoque.getQuantity()) {
						throw new Exception("Estoque insuficiente");
					}
					saida.add(productEstoque);
					total = total.add(productEstoque.getPrice().multiply(BigDecimal.valueOf(productCompra.getQuantity())));
				}
			}
		}
		productPurchesedDTO.setProducts(saida);
		productPurchesedDTO.setTotal(total);
		productPurchaseResponse.setTicket(productPurchesedDTO);
		return productPurchaseResponse;
	}

	public Optional<Product> findById(Long id) throws IOException{
		return list().stream().filter(a -> a.getProductId().equals(id)).findFirst();
	}
}
