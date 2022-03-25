package br.com.mercadolivre.service;

import br.com.mercadolivre.model.Customer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomerNameValidate implements Validator{

    private Customer customer;
    private final Integer TRES = 3;

    @Override
    public void valida() throws ValidatorException {
        if(customer.getName().length() < TRES) {
            throw new ValidatorException("O campo 'name' deve ter 3 ou mais characters");
        }
    }
}
