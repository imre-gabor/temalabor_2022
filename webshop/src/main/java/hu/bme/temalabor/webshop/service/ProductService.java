package hu.bme.temalabor.webshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.bme.temalabor.webshop.model.Product;
import hu.bme.temalabor.webshop.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Product updateProduct(Product product) {
        if(product.getId() == null || !productRepository.existsById(product.getId()))
            throw new IllegalArgumentException("Product with given id does not exist:" + product.getId());
        
        return productRepository.save(product);
    }
}
