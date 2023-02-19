package com.shopping.shop.category;

import com.shopping.shop.product.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CategoryService implements ICategoryService{

    private final CategoryRepository repository;
    @Override
    public void save(Category category) {
        repository.save(category);
    }

    @Override
    public Category getById(Long id) {
        Optional<Category> category = repository.findById(id);
        return category.get();
    }

    @Override
    public List<Category> getAll() {
        return (List<Category>) repository.findAll();
    }

    @Override
    public Category update(Long id, Category category) {
        Category category1;
        category1 = getById(id);
        category1.setCategoryName(category.getCategoryName());
        save(category1);
        return category1;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Category> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Category> categories = repository.findAll(pageRequest);
        return categories.getContent();
    }
}