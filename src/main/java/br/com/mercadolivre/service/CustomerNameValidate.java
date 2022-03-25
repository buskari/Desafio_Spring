package br.com.mercadolivre.service;

import br.com.mercadolivre.model.Customer;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CustomerNameValidate implements Validator{

    private List<Customer> customers;
    private final Integer TRES = 3;

    @Override
    public void valida() throws ValidatorException {
        for (Customer customer : customers) {
            if(customer.getName().length() < TRES) {
                throw new ValidatorException("O campo 'name' deve ter 3 ou mais characters");
            }
        }
    }
}
