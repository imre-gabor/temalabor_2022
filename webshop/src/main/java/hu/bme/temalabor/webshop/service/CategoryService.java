package hu.bme.temalabor.webshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.bme.temalabor.webshop.model.Category;
import hu.bme.temalabor.webshop.model.Product;
import hu.bme.temalabor.webshop.repository.CategoryRepository;
import hu.bme.temalabor.webshop.repository.ProductRepository;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Transactional
    public Product createProductInCategory(int categoryId, Product product) {
        Category category = categoryRepository.findById(categoryId).get();
        category.addProduct(product);
        return productRepository.save(product);
    }

}
