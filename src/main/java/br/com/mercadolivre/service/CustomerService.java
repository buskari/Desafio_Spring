package br.com.mercadolivre.service;

import br.com.mercadolivre.model.Customer;
import br.com.mercadolivre.util.FileUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CustomerService {

    final String JSON_CUSTOMERS_PATH = "./customers.json";

    ObjectMapper mapper = new ObjectMapper();


    public List<Customer> list() throws IOException {


        FileUtils<Customer> fileUtils = new FileUtils<>();
        List<Customer> list = fileUtils.readObjectsFromFile(JSON_CUSTOMERS_PATH);
        List<Customer> convertToCustomerAgain = mapper.convertValue(list, new TypeReference<List<Customer>>() {
        });

        return convertToCustomerAgain;

    }

    public Customer create(Customer customer, CustomerNameValidate customerNameValidate) throws IOException {
        FileUtils<Customer> fileUtils = new FileUtils<>();
        List<Customer> list = fileUtils.readObjectsFromFile(JSON_CUSTOMERS_PATH);

        customerNameValidate.valida();
        list.add(customer);
        fileUtils.writeObjectToFile(list, JSON_CUSTOMERS_PATH);

        return customer;
    }

}
