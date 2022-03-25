package br.com.mercadolivre.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.mercadolivre.controller.advice.OrderException;
import br.com.mercadolivre.controller.advice.EntityAlreadyExistsException;
import br.com.mercadolivre.dto.ProductPurchasedRequestDTO;
import br.com.mercadolivre.dto.ProductPurchasedResponseDTO;
import br.com.mercadolivre.model.Product;
import br.com.mercadolivre.model.ProductPurchaseRequest;
import br.com.mercadolivre.model.ProductPurchaseResponse;
import br.com.mercadolivre.util.FileUtils;

@Service
public class ProductService {

	@Autowired
	private FileService<Product> fileService;
	private static final String JSON_PRODUCTS_PATH = "./products.json";
	private final ObjectMapper mapper = new ObjectMapper();

	/**
	 * Cria uma lista de produtos e adiciona a lista existente
	 * @param products lista de produtos para inserção
	 * @param validadores lista de validadores
	 * @throws IOException exceção no caso de não conseguir ler a lista json
	 */
	public void create(List<Product> products, List<Validator> validadores) throws IOException {
		FileUtils<Product> fileUtils = new FileUtils<>();
		List<Product> list = fileUtils.readObjectsFromFile(JSON_PRODUCTS_PATH);
		List<Product> pojo = mapper.convertValue(list, new TypeReference<>() {
		});

		for (Product newProduct : products) {
			for (Product product : pojo) {
				if (product.getProductId().equals(newProduct.getProductId())) {
					throw new EntityAlreadyExistsException(
							"Produto com id " + newProduct.getProductId() + " já cadastrado!");
				}
			}
		}

		validadores.forEach(Validator::valida);
		list.addAll(products);
		fileUtils.writeObjectToFile(list, JSON_PRODUCTS_PATH);
	}

	/**
	 * Busca produtos conforme o filtro selecionado ou todos os produtos caso não haja filtro
	 * @param category String - categoria do produto
	 * @param name String - nome do produto
	 * @param brand String - marca do produto
	 * @param price BigDecimal - preço do produto
	 * @param quantity Integer - quantidade do produto
	 * @param freeShipping String - frete grátis referente ao produto
	 * @param prestige String - avaliação do produto
	 * @param order Integer - Parametro para ordenação, valores válidos: 0 - Alfabético crescente, 1 - Alfabético decrescente, 2 - Maior a menor preço ou 3 - Menor a maior preço
	 * @return Lista de produtos conforme o filtro selecionado
	 * @throws IOException exceção no caso de não conseguir ler a lista json
	 */
	public List<Product> findProducts(String category, String name, String brand, BigDecimal price, Integer quantity,
			String freeShipping, String prestige, Integer order) throws IOException {
		List<Product> fileProducts = fileService.findAll(JSON_PRODUCTS_PATH);
		List<Product> pojos = mapper.convertValue(fileProducts, new TypeReference<>() {
		});

		if (category != null) {
			pojos = pojos.stream().filter(product -> product.getCategory().equalsIgnoreCase(category))
					.collect(Collectors.toList());
		}

		if (name != null) {
			pojos = pojos.stream().filter(product -> product.getName().equalsIgnoreCase(name))
					.collect(Collectors.toList());
		}

		if (brand != null) {
			pojos = pojos.stream().filter(product -> product.getBrand().equalsIgnoreCase(brand))
					.collect(Collectors.toList());
		}

		if (price != null) {
			pojos = pojos.stream().filter(product -> product.getPrice().equals(price)).collect(Collectors.toList());
		}

		if (quantity != null) {
			pojos = pojos.stream().filter(product -> product.getQuantity().equals(quantity))
					.collect(Collectors.toList());
		}

		if (freeShipping != null) {
			boolean booleanFreeShipping = false;
			if (freeShipping.equalsIgnoreCase("sim")) {
				booleanFreeShipping = true;
			}
			boolean finalBooleanFreeShipping = booleanFreeShipping;
			pojos = pojos.stream().filter(product -> product.getFreeShipping().equals(finalBooleanFreeShipping))
					.collect(Collectors.toList());
		}

		if (prestige != null) {
			pojos = pojos.stream().filter(product -> product.getPrestige().equals(prestige))
					.collect(Collectors.toList());
		}

		if (order != null) {
			switch (order) {
			case 0:
				pojos = pojos.stream().sorted(Comparator.comparing(Product::getName)).collect(Collectors.toList());
				break;
			case 1:
				pojos = pojos.stream().sorted((p1, p2) -> p2.getName().compareTo(p1.getName()))
						.collect(Collectors.toList());
				break;
			case 2:
				pojos = pojos.stream().sorted(Comparator.comparing(Product::getPrice)).collect(Collectors.toList());
				break;
			case 3:
				pojos = pojos.stream().sorted((v1, v2) -> v2.getPrice().compareTo(v1.getPrice()))
						.collect(Collectors.toList());
				break;

			default:
				throw new InvalidParameterException(
						"Valores válidos para order: 0 - Alfabético crescente, 1 - Alfabético decrescente, 2 - Maior a menor preço ou 3 - Menor a maior preço");
			}
		}
		return pojos;

	}

	/**
	 * Busca todos os produtos
	 * @return lista de produtos recuperada do json
	 * @throws IOException exceção no caso de não conseguir ler a lista json
	 */
	public List<Product> list() throws IOException {
		FileUtils<Product> fileUtils = new FileUtils<>();
		List<Product> list = fileUtils.readObjectsFromFile(JSON_PRODUCTS_PATH);
		return mapper.convertValue(list, new TypeReference<List<Product>>() {
		});
	}

	/**
	 * Pedido de compra
	 * @param products objeto do tipo ProductPurchaseRequest com uma lista de produtos
	 * @return lista de produtos da venda
	 * @throws Exception exceção no caso de não conseguir ler a lista json
	 */
	public ProductPurchaseResponse newPurchase(ProductPurchaseRequest products) throws Exception {
		List<Product> saida = new ArrayList<>();
		BigDecimal total = BigDecimal.valueOf(0);
		List<Product> estoque = list();
		ProductPurchasedResponseDTO productPurchesedDTO = new ProductPurchasedResponseDTO();
		ProductPurchaseResponse productPurchaseResponse = new ProductPurchaseResponse();

		for (ProductPurchasedRequestDTO productCompra : products.getPurchasedProducts()) {
			Optional<Product> opt = findById(productCompra.getProductId());
			if (opt.isEmpty()) {
				throw new OrderException("Produto inexistente");
			}
			for (Product productEstoque : estoque) {
				if (productCompra.getProductId().equals(productEstoque.getProductId())) {
					if (productCompra.getQuantity() > productEstoque.getQuantity()) {
						throw new OrderException("Estoque insuficiente");
					}
					saida.add(productEstoque);
					total = total
							.add(productEstoque.getPrice().multiply(BigDecimal.valueOf(productCompra.getQuantity())));
				}
			}
		}
		productPurchesedDTO.setProducts(saida);
		productPurchesedDTO.setTotal(total);
		productPurchaseResponse.setTicket(productPurchesedDTO);
		return productPurchaseResponse;
	}

	/**
	 * Busca um produto por id
	 * @param id Long - id do produto
	 * @return um produto caso exista na lista
	 * @throws IOException exceção no caso de não conseguir ler a lista json
	 */
	public Optional<Product> findById(Long id) throws IOException {
		return list().stream().filter(a -> a.getProductId().equals(id)).findFirst();
	}
}
