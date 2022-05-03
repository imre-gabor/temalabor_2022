package hu.bme.temalabor.webshop.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.bme.temalabor.webshop.dto.ProductDto;
import hu.bme.temalabor.webshop.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    List<ProductDto> productsToDtos(List<Product> products);

    @Mapping(target = "category.products", ignore = true)
    ProductDto productToDto(Product product);

    Product dtoToProduct(ProductDto product);
    
}
