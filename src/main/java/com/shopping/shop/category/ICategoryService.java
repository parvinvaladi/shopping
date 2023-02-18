package com.shopping.shop.category;

import com.shopping.shop.product.Product;

import java.util.List;

public interface ICategoryService {

    void save(Category category);
    Category getById(Long id);
    List<Category> getAll();
    Category update(Long id ,Category category);
    void delete(Long id);
}
