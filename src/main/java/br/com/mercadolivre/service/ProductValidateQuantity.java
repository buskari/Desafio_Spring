package br.com.mercadolivre.service;

import br.com.mercadolivre.model.Product;

import java.util.List;


public class ProductValidateQuantity implements Validator {

    private List<Product> products;

    public ProductValidateQuantity(List<Product> products) {
        this.products = products;
    }

    /**
     * Valida se a quantidade do produto é menor ou igual a zero
     * @throws ValidatorException exceção no caso da quantidade do produto ser menor ou igual a zero
     */
    @Override
    public void valida() throws ValidatorException {
        for (Product product : products) {
            if (product.getQuantity() <= 0)
                throw new ValidatorException("a quantidade minima deve ser maior que 0");
        }
    }
}
