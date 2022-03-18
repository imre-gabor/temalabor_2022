package hu.bme.temalabor.webshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hu.bme.temalabor.webshop.service.DiscountService;
import hu.bme.temalabor.webshop.service.InitDbService;

@SpringBootApplication
public class WebshopApplication implements CommandLineRunner {

    @Autowired
    InitDbService initDbService;
    
    @Autowired
    DiscountService discountService;
    
	public static void main(String[] args) {
	    
		SpringApplication.run(WebshopApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
//        initDbService.addSampleData();
//        discountService.discountProductsInCategory("catagory1", 10);
    }

}
