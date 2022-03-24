package br.com.mercadolivre.controller;

import br.com.mercadolivre.model.Customer;
import br.com.mercadolivre.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
