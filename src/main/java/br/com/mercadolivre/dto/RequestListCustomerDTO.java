package br.com.mercadolivre.dto;

import br.com.mercadolivre.model.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RequestListCustomerDTO {

    private List<Customer> customers;
}
