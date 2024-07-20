package com.shopping.shop.category;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CategoryController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryMapper mapper;

    @MockBean
    private CategoryService service;

    @Order(1)
    @Test
    public void testFindAll()throws Exception{
        Category category1 = new Category();
        category1.setCategoryName("test1");

        Category category2 = new Category();
        category2.setCategoryName("test2");
        List<Category> categories = List.of(category1,category2);

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryName("categoryDTO1");

        CategoryDTO categoryDTO1 = new CategoryDTO();
        categoryDTO1.setCategoryName("categoryDTO2");
        List<CategoryDTO> categoryDTOS = List.of(categoryDTO,categoryDTO1);

        when(service.getAll()).thenReturn(categories);
        when(mapper.toCategoryDtos(categories)).thenReturn(categoryDTOS);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/category/").accept(MediaType.APPLICATION_JSON));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$.*",isA(ArrayList.class)))
                .andExpect(jsonPath("$[0].categoryName", Matchers.is("categoryDTO1")));

    }
}
