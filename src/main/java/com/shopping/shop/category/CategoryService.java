package com.shopping.shop.category;

import com.shopping.shop.product.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CategoryService implements ICategoryService{

    private final CategoryRepository repository;

    @Override
    @Caching(evict = {
            @CacheEvict(value = "one",allEntries = true),
            @CacheEvict(value = "two",allEntries = true)
    })
    public void save(Category category) {
        repository.save(category);
    }

    @Override
    @Cacheable(value = "two",key = "#id")
    public Category getById(Long id) {
        Optional<Category> category = repository.findById(id);
        return category.get();
    }

    @Override
    @Cacheable(value = "one")
    public List<Category> getAll() {
        return (List<Category>) repository.findAll();
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "two",key = "#id")
    })
    public Category update(Long id, Category category) {
        Category category1;
        category1 = getById(id);
        category1.setCategoryName(category.getCategoryName());
        save(category1);
        return category1;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "one",allEntries = true),
            @CacheEvict(value = "two",allEntries = true)
    })
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Cacheable(value = "one")
    public List<Category> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Category> categories = repository.findAll(pageRequest);
        return categories.getContent();
    }

    @Override
    @Cacheable(value = "one")
    public Collection<Category> getAllCategoriesJPQL() {
        Collection<Category> categories = repository.getAllCategoriesJPQL();
        return categories;
    }

    @Override
    public List<Category> getAllCategoriesSort() {
        List<Category> categories = repository.getAllCategoriesSort(Sort.by("categoryName"));
        return categories;
    }

    @Override
    public List<Category> getAllCategoriesNative() {
        List<Category> categories = repository.getAllCategoriesNative();
        return categories;
    }

    @Override
    public Integer getCountOfCategory() {
        return repository.getCountOfCategory();
    }

    @Override
    public List<Category> getCategoryByName(String name) {
        return repository.getCategoryByName(name);
    }

    @Override
    public List<Category> getAllUsingJoin() {
        return repository.getAllUsingJoin();
    }
}
