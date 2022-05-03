package hu.bme.temalabor.webshop.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import hu.bme.temalabor.webshop.dto.CategoryDto;
import hu.bme.temalabor.webshop.dto.ProductDto;
import hu.bme.temalabor.webshop.model.Category;
import hu.bme.temalabor.webshop.model.Product;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @IterableMapping(qualifiedByName = "noproducts")
    List<CategoryDto> categoriesToDtosNoProducts(List<Category> categories);

    List<CategoryDto>  categoriesToDtosWithProducts(List<Category> categories);

    
    @Mapping(target = "products", ignore = true)
    @Named("noproducts")
    CategoryDto categoryToDtoNoProducts(Category category);

    CategoryDto categoryToDtoWithProducts(Category category);
    
    
    @Mapping(target = "category", ignore = true)
    ProductDto productToDto(Product product);

}
