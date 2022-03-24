package br.com.mercadolivre.service;

import br.com.mercadolivre.model.Customer;
import br.com.mercadolivre.model.Product;
import br.com.mercadolivre.util.FileUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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


}
