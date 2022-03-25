package br.com.mercadolivre.controller;

import br.com.mercadolivre.model.Customer;
import br.com.mercadolivre.service.CustomerNameValidate;
import br.com.mercadolivre.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    public CustomerService service;

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getCustomers () throws IOException {

        List<Customer> customers = service.list();
        return ResponseEntity.ok(customers);

    }

    @PostMapping("/customers")
    public ResponseEntity<Customer> createCustomer(@RequestBody @NotNull Customer customer) throws IOException {
//        List<Validator> validators = Arrays.asList(
//                new CustomerNameValidate(customer),
//                new CustomerStateValidate(customer)
//        );
        service.create(customer, new CustomerNameValidate(customer));
        return ResponseEntity.ok(customer);
    }
}
