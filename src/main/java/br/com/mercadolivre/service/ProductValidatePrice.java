package br.com.mercadolivre.service;

import br.com.mercadolivre.model.Product;

import java.math.BigDecimal;
import java.util.List;

public class ProductValidatePrice implements Validator{

    private List<Product> products;

    public ProductValidatePrice(List<Product> products) {
        this.products = products;
    }

    /**
     * Valida se o preço do produto é menor ou igual a zero
     * @throws ValidatorException exceção no caso do preço do produto ser menor ou igual a zero
     */
    @Override
    public void valida() throws ValidatorException {
        for (Product product : products) {
            if (product.getPrice().compareTo(BigDecimal.ZERO) <= 0 )
                throw new ValidatorException("O valor minimo para o produto é 0");
        }
    }
}
