package com.shopping.shop.product;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public interface IProductService {

    void save(Product product);
    Product getById(Long id);
    List<Product> getAll();
    Product update(Long id ,Product product);
    void delete(Long id);

    List<Product> findByName(Optional<String> name,int page,int size);
}
