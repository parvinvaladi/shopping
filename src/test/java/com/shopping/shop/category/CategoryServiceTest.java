package com.shopping.shop.category;

import org.h2.command.dml.MergeUsing;
import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategoryServiceTest {

    @Mock
    CategoryRepository repository;

    @InjectMocks
    CategoryService service;

    @Order(1)
    @Test
    public void save()throws Exception{
        Category category = new Category();
        category.setCategoryName("category");
        when(repository.save(any(Category.class))).thenReturn(category);
        Category savedCategory = repository.save(category);
        assertNotNull(savedCategory);
        Assert.assertEquals(category.getCategoryName(),savedCategory.getCategoryName());
    }

    @Order(2)
    @Test
    public void findAllTest()throws Exception{
        Category category1 = new Category();
        category1.setCategoryName("test1");
        Category category2 = new Category();
        category2.setCategoryName("test2");
        List<Category> categories = List.of(category1,category2);

        when(repository.findAll()).thenReturn(categories);
        List<Category> queryResult = service.getAll();

        assertFalse(queryResult.isEmpty());
        assertNotNull(queryResult.get(0));
    }
}
