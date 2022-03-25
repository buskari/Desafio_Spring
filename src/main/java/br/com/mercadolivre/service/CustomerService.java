package br.com.mercadolivre.service;

import br.com.mercadolivre.controller.advice.EntityAlreadyExistsException;
import br.com.mercadolivre.model.Customer;
import br.com.mercadolivre.util.FileUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private FileService<Customer> fileService;
    final String JSON_CUSTOMERS_PATH = "./customers.json";

    ObjectMapper mapper = new ObjectMapper();


    public List<Customer> list() throws IOException {


        FileUtils<Customer> fileUtils = new FileUtils<>();
        List<Customer> list = fileUtils.readObjectsFromFile(JSON_CUSTOMERS_PATH);
        return mapper.convertValue(list, new TypeReference<>() {
        });
    }

    public List<Customer> create(List<Customer> newCustomers) throws IOException {
        FileUtils<Customer> fileUtils = new FileUtils<>();
        List<Customer> customers = fileUtils.readObjectsFromFile(JSON_CUSTOMERS_PATH);
        List<Customer> pojo = mapper.convertValue(customers, new TypeReference<>() {
        });

        for (Customer newCustomer : newCustomers) {
            for (Customer customer : pojo) {
                if (customer.getCustomerId().equals(newCustomer.getCustomerId())) {
                    throw new EntityAlreadyExistsException(
                            "Cliente com id " + newCustomer.getCustomerId() + " já cadastrado!");
                }
            }
        }

        customers.addAll(newCustomers);
        fileUtils.writeObjectToFile(customers, JSON_CUSTOMERS_PATH);
        return customers;
    }

    public List<Customer> findCustomer(String state) throws IOException {
        List<Customer> customerList = list();

        if(state != null) {
            return customerList
                    .stream()
                    .filter(
                            customer -> customer.getState().name().equalsIgnoreCase(state)
                    ).collect(Collectors.toList());
        }

        return customerList;
    }
}
