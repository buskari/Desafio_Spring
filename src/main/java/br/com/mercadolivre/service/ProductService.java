package br.com.mercadolivre.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

		List<Product> pojos = mapper.convertValue(fileProducts, new TypeReference<>() {
		});

		return pojos.stream().filter(product -> product.getCategory().equals(category)).collect(Collectors.toList());

	}

	public List<Product> catEName(List<Product> products, String categoria, String name) throws IOException {
		return products.stream().filter(a -> a.getCategory().equalsIgnoreCase(categoria))
				.filter(a -> a.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
	}

	public List<Product> list() throws IOException {
		FileUtils<Product> fileUtils = new FileUtils<>();
		List<Product> list = fileUtils.readObjectsFromFile(JSON_PRODUCTS_PATH);
		List<Product> pojos = mapper.convertValue(list, new TypeReference<List<Product>>() {
		});
		return pojos;
	}

	public List<Product> findProducts(
			String category,
			String name,
			String brand,
			BigDecimal price,
			Integer quantity,
			String freeShipping,
			String prestige,
			Integer order
	) throws IOException {
 
		List<Product> fileProducts = fileService.findAll(JSON_PRODUCTS_PATH);

		List<Product> pojos = mapper.convertValue(fileProducts, new TypeReference<List<Product>>() {
		});
		
		if(category != null ) {
			pojos = pojos
					.stream()
					.filter(product -> product.getCategory().equalsIgnoreCase(category))
					.collect(Collectors.toList());
		}

		if(name != null ) {
			pojos = pojos
					.stream()
					.filter(product -> product.getName().equalsIgnoreCase(name))
					.collect(Collectors.toList());
		}

		if(brand != null ) {
			pojos = pojos
					.stream()
					.filter(product -> product.getBrand().equalsIgnoreCase(brand))
					.collect(Collectors.toList());
		}

		if(price != null ) {
			pojos = pojos
					.stream()
					.filter(product -> product.getPrice().equals(price))
					.collect(Collectors.toList());
		}

		if(quantity != null ) {
			pojos = pojos
					.stream()
					.filter(product -> product.getQuantity().equals(quantity))
					.collect(Collectors.toList());
		}

		if(freeShipping != null ) {
			boolean booleanFreeShipping = false;
			if (freeShipping.equalsIgnoreCase("sim")) {
				booleanFreeShipping = true;
			}
			boolean finalBooleanFreeShipping = booleanFreeShipping;
			pojos = pojos
					.stream()
					.filter(product -> product.getFreeShipping().equals(finalBooleanFreeShipping))
					.collect(Collectors.toList());
		}

		if(prestige != null ) {
			pojos = pojos
					.stream()
					.filter(product -> product.getPrestige().equals(prestige))
					.collect(Collectors.toList());
		}

		if(order != null) {
			switch (order) {
			case 0:
				pojos = pojos
						.stream()
						.sorted(Comparator.comparing(Product::getName))
						.collect(Collectors.toList());
				break;
			case 1:
				pojos = pojos
						.stream()
						.sorted((p1, p2) -> p2.getName().compareTo(p1.getName()))
						.collect(Collectors.toList());
				break;
			case 2:
				pojos = pojos
						.stream()
						.sorted(Comparator.comparing(Product::getPrice))
						.collect(Collectors.toList());
				break;
			case 3:
				pojos = pojos
						.stream()
						.sorted((v1, v2) -> v2.getPrice().compareTo(v1.getPrice()))
						.collect(Collectors.toList());
				break;

			default:
				throw new RuntimeException("Wrong param value");
			}
		}

		return pojos;
	}

}
