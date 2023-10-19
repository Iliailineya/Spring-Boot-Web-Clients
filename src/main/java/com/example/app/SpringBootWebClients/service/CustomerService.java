package com.example.app.SpringBootWebClients.service;

import com.example.app.SpringBootWebClients.entity.Customer;
import com.example.app.SpringBootWebClients.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository repository;

    public List<Customer> getCustomers() throws URISyntaxException, IOException {
        return repository.getCustomers();
    }
}
