package com.jai.jpa.repos;

import com.jai.jpa.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 👈 important
class ProductRepoTest {

    @Autowired
    private ProductRepo productRepo;

    @Test
    void testSaveProduct() {
        // given
        Product product = new Product();
        product.setName("Laptop");
        product.setDescription("High performance laptop");
        product.setPrice(BigDecimal.valueOf(1200.00));
        product.setQuantity(10);
        product.setCategory("Electronics");
        product.setSku("LAP-12345");
        product.setAvailable(true);

        // when
        Product savedProduct = productRepo.save(product);

        // then
        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getId()).isNotNull();
        assertThat(savedProduct.getName()).isEqualTo("Laptop");
    }
}
