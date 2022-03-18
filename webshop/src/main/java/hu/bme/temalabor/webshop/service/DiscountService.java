package hu.bme.temalabor.webshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.bme.temalabor.webshop.repository.CategoryRepository;

@Service
public class DiscountService {

    @Autowired
    private CategoryRepository categoryRepository;
    
    
    @Transactional
    public void discountProductsInCategory(String categoryName, int percent) {
    
        categoryRepository.findByName(categoryName)
            .ifPresent(category -> 
                category.getProducts()
                    .forEach(
                        product -> product.setPrice(product.getPrice() * (100.0-percent)/100)));
    }
}
