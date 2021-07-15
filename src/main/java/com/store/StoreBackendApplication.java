package com.store;

import com.store.entity.Client;
import com.store.entity.ProductCategory;
import com.store.repository.ClientRepository;
import com.store.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class StoreBackendApplication implements CommandLineRunner {

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	ProductCategoryRepository productCategoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(StoreBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Client client1 = new Client(null, "Ivson", "Santos", "ivson@gmail.com");
		Client client2 = new Client(null, "Belgium", "Brussels", "belgium@gmail.com");
		clientRepository.saveAll(Arrays.asList(client1, client2));

		ProductCategory productCategory1 = new ProductCategory(null, "Computers");
		ProductCategory productCategory2 = new ProductCategory(null, "Books");
		productCategoryRepository.saveAll(Arrays.asList(productCategory1, productCategory2));


	}

}
