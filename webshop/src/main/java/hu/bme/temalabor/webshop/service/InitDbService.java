package hu.bme.temalabor.webshop.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.bme.temalabor.webshop.model.Category;
import hu.bme.temalabor.webshop.model.Product;
import hu.bme.temalabor.webshop.repository.CategoryRepository;
import hu.bme.temalabor.webshop.repository.ProductRepository;

@Service
public class InitDbService {

    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Transactional
    public void addSampleData() {
        
        Category category = new Category("catagory1");
        categoryRepository.save(category);
        
        Product product1 = new Product("Prod1", 100);
        Product product2 = new Product("Prod2", 200);
        
        category.addProduct(product1);
        category.addProduct(product2);
        
        productRepository.saveAll(Arrays.asList(product1, product2));
    }
    
}
