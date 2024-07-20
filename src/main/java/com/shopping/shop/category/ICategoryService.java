package com.shopping.shop.category;

import com.shopping.shop.product.Product;
import org.springframework.data.domain.Sort;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ICategoryService {

    void save(Category category);
    Category getById(Long id);
    List<Category> getAll();
    Category update(Long id ,Category category);
    void delete(Long id);
    List<Category> findAll(int page,int size);

    Collection<Category> getAllCategoriesJPQL();
    List<Category> getAllCategoriesNative();
    Integer getCountOfCategory();

    List<Category> getAllCategoriesSort();
    List<Category> getCategoryByName(String name);

    List<Category> getAllUsingJoin();
}
