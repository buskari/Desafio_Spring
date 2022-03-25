package br.com.mercadolivre.service;

import java.util.List;

import br.com.mercadolivre.model.Product;

public class ProductNotNullValidation implements Validator {
	private List<Product> products;

	public ProductNotNullValidation(List<Product> products) {
		this.products = products;
	}

	/**
	 * Valida se nenhum campo do produto é igual a nulo
	 * @throws ValidatorException exceção no caso de algum campo do produto ser nulo
	 */
	@Override
	public void valida() throws ValidatorException {
		for (Product product : products) {
			if(product.getBrand() == null 
					|| product.getCategory() == null 
					|| product.getFreeShipping()  == null 
					|| product.getName() == null 
					|| product.getPrestige()  == null 
					|| product.getPrice()  ==  null 
					|| product.getProductId() == null 
					|| product.getQuantity() == null) {
				throw new ValidatorException("Todos os campos devem ser preenchidos!");
			}
		}
	}
}
