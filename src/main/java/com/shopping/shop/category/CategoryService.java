package com.shopping.shop.category;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService implements ICategoryService{

    private final CategoryRepository repository;
    @Override
    public void save(Category category) {
        repository.save(category);
    }

    @Override
    public Category getById(Long id) {
        return null;
    }

    @Override
    public List<Category> getAll() {
        return null;
    }

    @Override
    public Category update(Long id, Category category) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
