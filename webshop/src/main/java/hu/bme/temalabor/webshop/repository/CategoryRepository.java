package hu.bme.temalabor.webshop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.bme.temalabor.webshop.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

    Optional<Category> findByName(String categoryName);

    @EntityGraph(attributePaths = "products")
    @Query("SELECT c FROM Category c WHERE c.name = :categoryName")
    Optional<Category> findByNameWithProducts(String categoryName);

    
    @EntityGraph(attributePaths = "products")
    @Query("SELECT c FROM Category c")
    List<Category> findAllWithProducts();
}
