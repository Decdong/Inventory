package com.apex.eqp.inventory.services;

import com.apex.eqp.inventory.entities.Product;
import com.apex.eqp.inventory.entities.RecalledProduct;
import com.apex.eqp.inventory.helpers.ProductFilter;
import com.apex.eqp.inventory.repositories.InventoryRepository;
import com.apex.eqp.inventory.repositories.RecalledProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final InventoryRepository inventoryRepository;
    private final RecalledProductRepository recalledProductRepository;

    public Product save(Product product) {
        return inventoryRepository.save(product);
    }

    public Collection<Product> getAllProduct() {
//        ProductFilter filter = new ProductFilter(null   );
//
////        return filter.removeRecalled(inventoryRepository.findAll());

        Collection<Product> products=inventoryRepository.findAll();
        List<RecalledProduct> recalledProduct=recalledProductRepository.findAll();
        List<String> ls=new ArrayList<>();
        for (RecalledProduct rp :recalledProduct) {
            ls.add(rp.getName());
        }
        ProductFilter filter1 = new ProductFilter(ls);
        return filter1.removeRecalled(products,ls);

    }



    public Optional<Product> findById(Integer id) {
        return inventoryRepository.findById(id);
    }
}
