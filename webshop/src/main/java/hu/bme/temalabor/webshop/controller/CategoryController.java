package hu.bme.temalabor.webshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.bme.temalabor.webshop.dto.CategoryDto;
import hu.bme.temalabor.webshop.dto.ProductDto;
import hu.bme.temalabor.webshop.mapper.CategoryMapper;
import hu.bme.temalabor.webshop.mapper.ProductMapper;
import hu.bme.temalabor.webshop.repository.CategoryRepository;
import hu.bme.temalabor.webshop.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private CategoryMapper categoryMapper;
    
    @Autowired
    private ProductMapper productMapper;

    @GetMapping
    public List<CategoryDto> findAll(@RequestParam(required = false, defaultValue = "false") boolean withProducts){
        if(!withProducts) {
            return categoryMapper.categoriesToDtosNoProducts(categoryRepository.findAll());
        } else {
            return categoryMapper.categoriesToDtosWithProducts(categoryRepository.findAllWithProducts());
        }
    }
    
    @PostMapping("{categoryId}/products")
    public ProductDto createProductInCategory(@PathVariable int categoryId, @RequestBody ProductDto product) {
        return productMapper.productToDto(
                categoryService.createProductInCategory(categoryId, 
                        productMapper.dtoToProduct(product)));
    }
}
