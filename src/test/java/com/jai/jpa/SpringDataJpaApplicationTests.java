package com.jai.jpa;

import com.jai.jpa.entity.Product;
import com.jai.jpa.repos.ProductRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SpringDataJpaApplicationTests {

	@Autowired
	private ProductRepo productRepo;

	@Test
	void testSaveProduct() {
		Product product = new Product();
		product.setName("Phone");
		product.setDescription("Latest smartphone");
		product.setPrice(BigDecimal.valueOf(800.00));
		product.setQuantity(5);
		product.setCategory("Electronics");
		product.setSku("PHN-99999");
		product.setAvailable(true);

		Product saved = productRepo.save(product);

		assertThat(saved.getId()).isNotNull();
		assertThat(saved.getName()).isEqualTo("Phone");
	}

	@Test
	void testUpdateProduct() {
		// fetch an existing product (adjust ID to one that exists in your DB)
		Product product = productRepo.findById(2l).orElseThrow();

		// update fields
		product.setPrice(BigDecimal.valueOf(750.00));
		product.setQuantity(7);

		Product updated = productRepo.save(product);

		assertThat(updated.getPrice()).isEqualByComparingTo("750.00");
		assertThat(updated.getQuantity()).isEqualTo(7);
	}

	@Test
	void testFindByIdProduct() {
		// when
		Product found = productRepo.findById(2l)
				.orElseThrow(() -> new RuntimeException("Product not found"));

		// then
		assertThat(found).isNotNull();
		assertThat(found.getId()).isEqualTo(2l);
	}

	@Test
	void testSaveAllProducts() {
		// given
		Product product1 = new Product();
		product1.setName("Laptop");
		product1.setDescription("Gaming Laptop");
		product1.setPrice(BigDecimal.valueOf(1500.00));
		product1.setQuantity(8);
		product1.setCategory("Electronics");
		product1.setSku("LAP-22222");
		product1.setAvailable(true);

		Product product2 = new Product();
		product2.setName("Headphones");
		product2.setDescription("Wireless headphones");
		product2.setPrice(BigDecimal.valueOf(200.00));
		product2.setQuantity(20);
		product2.setCategory("Accessories");
		product2.setSku("HDP-33333");
		product2.setAvailable(true);

		List<Product> products = Arrays.asList(product1, product2);

		// when
		List<Product> savedProducts = productRepo.saveAll(products);

		// then
		assertThat(savedProducts).hasSize(2);
		assertThat(savedProducts.get(0).getId()).isNotNull();
		assertThat(savedProducts.get(1).getId()).isNotNull();
	}

	@Test
	void testFindAllProducts() {

		// when
		List<Product> products = productRepo.findAll();

		// then
		assertThat(products).isNotEmpty();

	}

	@Test
	void testDeleteProductById() {
		// when → delete by ID
		productRepo.deleteById(2l);

		// then → confirm it is deleted
		boolean exists = productRepo.findById(2l).isPresent();
		assertThat(exists).isFalse();
	}




}
