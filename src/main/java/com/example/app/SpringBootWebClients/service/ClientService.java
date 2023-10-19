package com.example.app.SpringBootWebClients.service;

import com.example.app.SpringBootWebClients.entity.Client;
import com.example.app.SpringBootWebClients.repository.ClientRepository;
import com.example.app.SpringBootWebClients.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class ClientService {

    @Autowired
    ClientRepository repository;

    public List<Client> getClients() {
        Iterable<Client> iterable = repository.findAll();
        List<Client> list =
                StreamSupport.stream(iterable.spliterator(), false)
                        .map(client -> new Client(client.getId(),
                                Constants.URL_IMAGES + client.getImg(),
                                client.getFirstName(),
                                client.getLastName(),
                                client.getPhone()))
                        .toList();
        return new ArrayList<>(list);
    }
}
