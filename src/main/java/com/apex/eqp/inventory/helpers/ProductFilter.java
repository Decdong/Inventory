package com.apex.eqp.inventory.helpers;

import com.apex.eqp.inventory.entities.Product;
import com.sun.xml.bind.v2.runtime.reflect.Lister;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;



public class ProductFilter {

    private final List<String> recalledProducts;

    public ProductFilter(List<String> recalledProducts) {
        if (recalledProducts == null) recalledProducts = new ArrayList<>();
        this.recalledProducts = recalledProducts;
    }

    public List<Product> removeRecalled(Collection<Product> allProduct, List<String> ls) {

        List<Product> res= new ArrayList<>();
        for (Product p: allProduct) {
            if ( filterByName(p,ls) ) res.add(p);
        }
        return res;
//        return allProduct.stream().filter(ProductFilter::filterByName).collect(Collectors.toList());
    }

    private static boolean filterByName(Product product,List<String> ls) {
        if (ls.contains(product.getName()) ) return false;
        return true;
    }


}




//
//public class ProductFilter {
//
//    private final List<String> recalledProducts=new ArrayList<>();
//   ProductFilter productFilter=new ProductFilter();
//
//    public ProductFilter() {
//
//    }
//
//
//
//    public List<String> getRecalledProducts() {
//        return recalledProducts;
//    }
//
//    public ProductFilter(List<String> recalledProducts) {
//        if (recalledProducts == null) recalledProducts = new ArrayList<>();
//
//        this.recalledProducts = recalledProducts;
//    }
//
//    public List<Product> removeRecalled(Collection<Product> allProduct) {
//        return allProduct.stream().filter(ProductFilter::filterByName).collect(Collectors.toList());
//    }
//
//    private static boolean filterByName(Product product ){
//        List<String> rp=new ProductFilter().getRecalledProducts();
//        if (rp.contains(product)) return false;
//        return true;
//    }
//}
