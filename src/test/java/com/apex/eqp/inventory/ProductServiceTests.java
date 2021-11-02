package com.apex.eqp.inventory;

import com.apex.eqp.inventory.entities.Product;
import com.apex.eqp.inventory.entities.RecalledProduct;
import com.apex.eqp.inventory.services.ProductService;
import com.apex.eqp.inventory.services.RecalledProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SpringBootTest
class  ProductServiceTests {

    @Autowired
    ProductService productService;

    @Autowired
    RecalledProductService recalledProductService;


    /**
     * Helper method to create test products
     */
    private Product createTestProduct(String productName, Double price, Integer quantity) {
        return Product.builder()
                .name(productName)
                .price(price)
                .quantity(quantity)
                .build();
    }

    /**
     * Helper method to create test recalled products
     */
    private RecalledProduct createTestRecalledProduct(String recalledProductName) {
        return RecalledProduct.builder()
                .name(recalledProductName)
                .build();
    }

    @Test
    void shouldSaveProduct() {
        Product product = createTestProduct("product1", 1.2, 2);

        Product savedProduct = productService.save(product);

        Product loadedProduct = productService.findById(savedProduct.getId()).orElse(null);

        Assertions.assertNotNull(loadedProduct);
        Assertions.assertNotNull(loadedProduct.getId());
    }

    @Test
    void shouldUpdateProduct() {
        Product product = createTestProduct("product2", 1.3, 5);

        Product savedProduct = productService.save(product);

        Product loadedProduct = productService.findById(savedProduct.getId()).orElse(null);

        Assertions.assertNotNull(loadedProduct);

        loadedProduct.setName("NewProduct");

        productService.save(loadedProduct);

        Assertions.assertNotNull(productService.findById(loadedProduct.getId()).orElse(null));
    }
    @Test
    void  shouldKeepOnlyInProduct(){
        Product product1=createTestProduct("A", 1.2, 6);
        Product product2=createTestProduct("B", 1.3, 16);
        Product product3=createTestProduct("C", 1.4, 26);
        Product product4=createTestProduct("D", 1.5, 26);
        Product product5=createTestProduct("E", 1.6, 36);
        Product savedProduct1=productService.save(product1);
        Product savedProduct2=productService.save(product2);
        Product savedProduct3=productService.save(product3);
        Product savedProduct4=productService.save(product4);
        Product savedProduct5=productService.save(product5);

        RecalledProduct recalledProduct1=createTestRecalledProduct("A");
        RecalledProduct recalledProduct2=createTestRecalledProduct("B");
        RecalledProduct saveRecallProduct1=recalledProductService.save(recalledProduct1);
        RecalledProduct saveRecallProduct2=recalledProductService.save(recalledProduct2);

        Collection <Product> collection= new ArrayList<>() ;
        collection.add(savedProduct3);
        collection.add(savedProduct4);
        collection.add(savedProduct5);

        Assertions.assertEquals(collection,productService.getAllProduct());


    }

}
