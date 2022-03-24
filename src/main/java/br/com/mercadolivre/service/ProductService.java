package br.com.mercadolivre.service;

import java.io.IOException;
import java.util.ArrayList;
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

		List<Product> pojos = mapper.convertValue(fileProducts, new TypeReference<List<Product>>() {
		});

		return pojos.stream().filter(product -> product.getCategory().equals(category)).collect(Collectors.toList());

	}

	public List<Product> catEName(List<Product> products, String categoria, String name) throws IOException { // filtra
																												// por
																												// categoria
																												// e
																												// nome
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

	public List<Product> findProducts(String category, Integer order) throws IOException {
 
		List<Product> fileProducts = fileService.findAll(JSON_PRODUCTS_PATH);

		List<Product> pojos = mapper.convertValue(fileProducts, new TypeReference<List<Product>>() {
		});
		
		if(category != null ) {
			pojos = pojos.stream().filter(product -> product.getCategory().equals(category))
			.collect(Collectors.toList());
		}
		
		if(order != null) {
			switch (order) {
			case 0:
				break;
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;

			default:
				break;
			}
		}
		

		return pojos;
	}

}
