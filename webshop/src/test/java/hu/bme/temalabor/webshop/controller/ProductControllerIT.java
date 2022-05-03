package hu.bme.temalabor.webshop.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import hu.bme.temalabor.webshop.dto.ProductDto;
import hu.bme.temalabor.webshop.repository.CategoryRepository;
import hu.bme.temalabor.webshop.repository.ProductRepository;

@SpringBootTest(webEnvironment =  WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@AutoConfigureWebTestClient
public class ProductControllerIT {

    @Autowired
    WebTestClient webTestClient;
    
    @Autowired
    CategoryRepository categoryRepository;
    
    @Autowired
    ProductRepository productRepository;
    
    @BeforeEach
    public void init() {
        productRepository.deleteAll();
        categoryRepository.deleteAll();
    }
    
    @Test
    void testThatProductCanBeCreated() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setName("testprod");
        productDto.setPrice(2000);
        productDto = createProductDto(productDto);
        
        ProductDto productFromDb = getProductById(productDto.getId());
        assertThat(productFromDb)
            .usingRecursiveComparison()
            .isEqualTo(productDto);
    }

    private ProductDto getProductById(Integer id) {
        return webTestClient
                .get()
                .uri("/api/products/" + id)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ProductDto.class)
                .returnResult().getResponseBody();
    }

    private ProductDto createProductDto(ProductDto productDto) {
        return webTestClient
        .post()
        .uri("/api/products")
        .bodyValue(productDto)
        .exchange()
        .expectStatus().isOk()
        .expectBody(ProductDto.class)
        .returnResult().getResponseBody();
    }
    
}
