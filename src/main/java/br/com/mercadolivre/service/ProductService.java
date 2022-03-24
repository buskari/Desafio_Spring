package br.com.mercadolivre.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.mercadolivre.model.Product;
import br.com.mercadolivre.util.FileUtils;

@Service
public class ProductService {

	private static final String JSON_PRODUCTS_PATH = "./products.json";

	public void create(List<Product> products) throws IOException {
		FileUtils<Product> fileUtils = new FileUtils<>();
		List<Product> list = fileUtils.readObjectsFromFile(JSON_PRODUCTS_PATH);
		list.addAll(products);
		fileUtils.writeObjectToFile(list, JSON_PRODUCTS_PATH);
	}

}
