package com.shopping.shop.product;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    Product save(Product product);
    Product getById(Long id);
    List<Product> getAll();
    Product update(Long id ,Product product);
    void delete(Long id);
}
