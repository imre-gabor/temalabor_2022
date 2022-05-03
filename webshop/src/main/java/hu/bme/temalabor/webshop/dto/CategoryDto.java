package hu.bme.temalabor.webshop.dto;

import java.util.List;

public class CategoryDto {

    private Integer id;
    private String name;
    
    private List<ProductDto> products;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

}
