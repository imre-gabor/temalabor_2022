package hu.bme.temalabor.webshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.bme.temalabor.webshop.dto.ProductDto;
import hu.bme.temalabor.webshop.mapper.ProductMapper;
import hu.bme.temalabor.webshop.model.Product;
import hu.bme.temalabor.webshop.repository.ProductRepository;
import hu.bme.temalabor.webshop.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @Autowired
    ProductMapper productMapper;

    @GetMapping
    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();
        return productMapper.productsToDtos(products);
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return productMapper.productToDto(product);
    }

    @PostMapping
    public ProductDto create(@RequestBody ProductDto product) {

        product.setId(null);
        return productMapper.productToDto(productRepository.save(productMapper.dtoToProduct(product)));
    }

    @PutMapping("/{id}")
    public ProductDto update(@PathVariable int id, @RequestBody ProductDto product) {
        try {
            product.setId(id);
            return productMapper.productToDto(
                    productService.updateProduct(
                            productMapper.dtoToProduct(product)));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
