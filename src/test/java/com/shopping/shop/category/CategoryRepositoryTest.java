package com.shopping.shop.category;

import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepository repository;

    @BeforeEach
    void init(){
        Category category = new Category();
        category.setCategoryName("category");
        Category saved = repository.save(category);
    }

    @AfterEach
    public void deleteAll(){
        repository.deleteAll();
    }

    @Order(1)
    @Test
    public void save() throws Exception{
        Category category = new Category();
        category.setCategoryName("test");
        Category savedCategory = repository.save(category);
        Assert.assertNotNull(savedCategory);

        Assert.assertEquals(category.getCategoryName(),savedCategory.getCategoryName());
    }

    @Order(2)
    @Test
    public void getAllTest() throws Exception{
        List<Category> categories = (List<Category>) repository.findAll();
        Assert.assertNotNull(categories.get(0));
    }
}
