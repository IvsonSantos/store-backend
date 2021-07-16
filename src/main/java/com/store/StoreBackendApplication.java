package com.store;

import com.store.entity.Client;
import com.store.entity.Product;
import com.store.entity.ProductCategory;
import com.store.repository.ClientRepository;
import com.store.repository.ProductCategoryRepository;
import com.store.repository.ProductRepository;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication
public class StoreBackendApplication implements CommandLineRunner {

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	ProductCategoryRepository productCategoryRepository;

	@Autowired
	ProductRepository productRepository;

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

		Product product1 = new Product(
				null,
				productCategory1,
				"MacBook Pro",
				"Laptop from Apple",
				new BigDecimal(2000),
				"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTB3IqPAe8pGmuE-3iRF27rzmM5QChkPn_OGLsrG4LJgKOwme757_euZpEgPQ&usqp=CAc",
				Boolean.TRUE,
				20,
				LocalDateTime.now(),
				null
				);
		Product product2 = new Product(
				null,
				productCategory1,
				"Playstation 5",
				"New generation of videogames",
				new BigDecimal(500),
				"https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcTyt01ZqpETv4gYUHqxGEf6KGiWCvlh0rZbJL5KMRV726XzAyerox8h4est7yOSxwwNXX0e-XLf2ss&usqp=CAc",
				Boolean.TRUE,
				0,
				LocalDateTime.now(),
				null
		);
		Product product3 = new Product(
				null,
				productCategory2,
				"Life of Pi",
				"Adventure Book",
				new BigDecimal(12),
				"https://prodimage.images-bn.com/pimages/9780156027328_p1_v4_s550x406.jpg",
				Boolean.TRUE,
				120,
				LocalDateTime.now(),
				null
		);
		productRepository.saveAll(Arrays.asList(product1, product2, product3));



	}

}
