package com.shopping.shop.product;

import com.shopping.shop.common.SearchCriteria;
import org.springframework.data.domain.Page;

import java.util.*;

public interface IProductService {

    void save(Product product);
    Product getById(Long id);
    List<Product> getAll();
    Product update(Long id ,Product product);
    void delete(Long id);

    Page<Product> findByCategoryId(Long id,int page,int size);
    Page<Product> findByName(Optional<String> name, int page, int size);

    List<Product> geAllProductOrdered();

    List<Product> getProduct1();
    List<Product> getProductByName(String name);

    List<Product> getAllUsingJoin();

    List<Product> getProductOfACategory(String name);

    List<Product> search(List<SearchCriteria> criteriaList);
}
