package hu.bme.temalabor.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.bme.temalabor.webshop.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
