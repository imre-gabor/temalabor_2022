package hu.bme.temalabor.webshop.dto;

public class ProductDto {

    private Integer id;
    private String name;
    private double price;
    private CategoryDto category;
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
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public CategoryDto getCategory() {
        return category;
    }
    public void setCategory(CategoryDto category) {
        this.category = category;
    }
    
    
}
