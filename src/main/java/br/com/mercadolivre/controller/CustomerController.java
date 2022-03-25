package br.com.mercadolivre.controller;

import br.com.mercadolivre.dto.RequestListCustomerDTO;
import br.com.mercadolivre.model.Customer;
import br.com.mercadolivre.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.xml.Jaxb2CollectionHttpMessageConverter;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CustomerController {

    @Autowired
    public CustomerService service;

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getCustomers (
            @RequestParam(required = false) String state
    ) throws IOException {

        List<Customer> customers = service.findCustomer(state);
        return ResponseEntity.ok(customers);
    }

    @PostMapping("/customers")
    public ResponseEntity<List<Customer>> createCustomer(
            @RequestBody @NotNull RequestListCustomerDTO customers
    ) throws IOException {
        List<Customer> customersList = service.create(customers.getCustomers());
        return new ResponseEntity<>(customersList, HttpStatus.CREATED);
    }
}
