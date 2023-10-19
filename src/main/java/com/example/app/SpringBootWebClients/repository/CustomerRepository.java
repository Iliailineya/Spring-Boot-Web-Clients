package com.example.app.SpringBootWebClients.repository;

import com.example.app.SpringBootWebClients.entity.Customer;
import com.example.app.SpringBootWebClients.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class CustomerRepository {

    private static final Logger LOGGER =
            Logger.getLogger(CustomerRepository.class.getName());

    List<Customer> list;

    public List<Customer> getCustomers() throws IOException {

        File jsonFile = new ClassPathResource(Constants.URL_FILES +
                Constants.FILE_COSTUMERS).getFile();
        String path = jsonFile.getAbsolutePath();

        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(path));
            list = gson.fromJson(reader,
                    new TypeToken<List<Customer>>() {}.getType());
            reader.close();
            return list.stream()
                    .map(tour -> new Customer(tour.getId(),
                            Constants.URL_IMAGES + tour.getImg(),
                            tour.getName(),
                            tour.getPhone())
                    )
                    .toList();
        } catch (Exception ex) {
            LOGGER.info("CustomerRepository msg: " + ex.getMessage());
            // Якщо помилка, повертаємо порожню колекцію
            return Collections.emptyList();
        }
    }
}
