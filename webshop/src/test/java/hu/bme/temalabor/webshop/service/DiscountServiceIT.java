package hu.bme.temalabor.webshop.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import hu.bme.temalabor.webshop.model.Category;
import hu.bme.temalabor.webshop.model.Product;
import hu.bme.temalabor.webshop.repository.CategoryRepository;
import hu.bme.temalabor.webshop.repository.ProductRepository;

@SpringBootTest
@AutoConfigureTestDatabase
public class DiscountServiceIT {

    @Autowired
    DiscountService discountService;
    
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
    void testThatDiscountProductsInCategorySetsLowerPricesForMatchingCategoryName() throws Exception {
        
        //ARRANGE
        String categoryName = "testcat";
        Category category = new Category(categoryName);
        Product product1 = new Product("test1", 100);
        category.addProduct(product1);
        Product product2 = new Product("test2", 200);
        category.addProduct(product2);
        
        categoryRepository.save(category);
        product1 = productRepository.save(product1);
        product2 = productRepository.save(product2);
        
        
        //ACT
        discountService.discountProductsInCategory(categoryName, 10);
        
        //ASSERT
//        assertThat(productRepository.findById(product1.getId()).get().getPrice()).isCloseTo(90.0, Offset.offset(0.0000001));
//        assertThat(productRepository.findById(product2.getId()).get().getPrice()).isCloseTo(180.0, Offset.offset(0.0000001));
        
        category = categoryRepository.findByNameWithProducts(categoryName).get();
        
        assertThat(category.getProducts().get(0).getPrice()).isCloseTo(90.0, Offset.offset(0.0000001));
        assertThat(category.getProducts().get(1).getPrice()).isCloseTo(180.0, Offset.offset(0.0000001));
        
        //ANNIHILATE: most nincs
    }
    
    @Test
    void testThatDiscountProductsInCategoryDoesNothingForNotFoundCategoryName() throws Exception {
        
        //ARRANGE
        String categoryName = "testcat";
        Category category = new Category(categoryName);
        Product product1 = new Product("test1", 100);
        category.addProduct(product1);
        Product product2 = new Product("test2", 200);
        category.addProduct(product2);
        
        categoryRepository.save(category);
        product1 = productRepository.save(product1);
        product2 = productRepository.save(product2);
        
        
        //ACT
        discountService.discountProductsInCategory("adfsdf", 10);
        
        //ASSERT
        assertThat(productRepository.findById(product1.getId()).get().getPrice()).isCloseTo(100.0, Offset.offset(0.0000001));
        assertThat(productRepository.findById(product2.getId()).get().getPrice()).isCloseTo(200.0, Offset.offset(0.0000001));
    }
}
