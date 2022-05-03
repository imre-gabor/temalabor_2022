package hu.bme.temalabor.webshop.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import hu.bme.temalabor.webshop.model.Category;
import hu.bme.temalabor.webshop.model.Product;
import hu.bme.temalabor.webshop.repository.CategoryRepository;

@ExtendWith(MockitoExtension.class)
public class DiscountServiceTest {

    @InjectMocks
    DiscountService discountService;
    
    @Mock
    CategoryRepository categoryRepository;
    
    
    @Test
    void testThatDiscountProductsInCategorySetsLowerPricesForMatchingCategoryName() throws Exception {
        
        //ARRANGE
        String categoryName = "testcat";
        Category category = new Category(categoryName);
        category.addProduct(new Product("test1", 100));
        category.addProduct(new Product("test2", 200));
        
        when(categoryRepository.findByName(categoryName)).thenReturn(Optional.of(category));
        
        //ACT
        discountService.discountProductsInCategory(categoryName, 10);
        
        //ASSERT
        assertThat(category.getProducts().get(0).getPrice()).isCloseTo(90.0, Offset.offset(0.0000001));
        assertThat(category.getProducts().get(1).getPrice()).isCloseTo(180.0, Offset.offset(0.0000001));
        
        
        //ANNIHILATE: most nincs
    }
    
    @Test
    void testThatDiscountProductsInCategoryDoesNothingForNotFoundCategoryName() throws Exception {
        
        //ARRANGE
        String categoryName = "testcat";
        Category category = new Category(categoryName);
        category.addProduct(new Product("test1", 100));
        category.addProduct(new Product("test2", 200));
        
        when(categoryRepository.findByName(categoryName)).thenReturn(Optional.empty());
        
        //ACT
        discountService.discountProductsInCategory(categoryName, 10);
        
        //ASSERT
        assertThat(category.getProducts().get(0).getPrice()).isCloseTo(100.0, Offset.offset(0.0000001));
        assertThat(category.getProducts().get(1).getPrice()).isCloseTo(200.0, Offset.offset(0.0000001));
        
        
        //ANNIHILATE: most nincs
    }
}
