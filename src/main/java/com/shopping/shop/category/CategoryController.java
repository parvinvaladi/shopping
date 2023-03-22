package com.shopping.shop.category;

import com.shopping.shop.product.IProductService;
import com.shopping.shop.product.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/v1/category")
@Slf4j
public class CategoryController {

    private final ICategoryService service;
    private CategoryMapper mapper;
    @Autowired
    private HttpServletRequest request;

    @PostMapping("/")
    public ResponseEntity<Void> save(@RequestBody CategoryDTO categoryDTO){

        Category category = mapper.toCategory(categoryDTO);
        service.save(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<CategoryDTO>> getAll(){
        List<Category> categories = service.getAll();
        List<CategoryDTO> categoryDTOS = mapper.toCategoryDtos(categories);
//        log.info(String.valueOf(request.getRemoteAddr()));  to get IP address of client
        return ResponseEntity.ok(categoryDTOS);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable Long id){
        Category category = service.getById(id);
        CategoryDTO categoryDTO = mapper.toCategoryDTO(category);
        return ResponseEntity.ok(categoryDTO);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO){
        Category category = mapper.toCategory(categoryDTO);
        Category category1 = service.update(id,category);
        return ResponseEntity.ok(mapper.toCategoryDTO(category));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteById(@PathParam("id") Long id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/categorypage")
    public ResponseEntity<List<CategoryDTO>> pagingCategory(@PathParam("page") int page, @PathParam("size") int size){
        List<Category> categories = service.findAll(page,size);
        List<CategoryDTO> categoryDTOS = mapper.toCategoryDtos(categories);
        return ResponseEntity.ok(categoryDTOS);
    }

    @GetMapping("/getAllCategoriesJpql")
    public ResponseEntity<List<CategoryDTO>> getAllCategoriesJpql(){
        List<Category> categories = (List<Category>) service.getAllCategoriesJPQL();
        List<CategoryDTO> categoryDTOS = mapper.toCategoryDtos(categories);
        return ResponseEntity.ok(categoryDTOS);
    }

    @GetMapping("/getallsort")
    public ResponseEntity<List<CategoryDTO>> getAllCategoriesSort(){
        List<Category> categories = service.getAllCategoriesSort();
        return ResponseEntity.ok(mapper.toCategoryDtos(categories));
    }

    @GetMapping("/getAllCategoriesNative")
    public ResponseEntity<List<CategoryDTO>> getAllCategoriesNative(){
        List<Category> categories = service.getAllCategoriesNative();
        List<CategoryDTO> categoryDTOS = mapper.toCategoryDtos(categories);
        return ResponseEntity.ok(categoryDTOS);
    }

    @GetMapping("/getcount")
    public ResponseEntity<Integer> getCountOfCategory(){
        return ResponseEntity.ok(service.getCountOfCategory());
    }

    @GetMapping("/getcategorybyname")
    public ResponseEntity<List<CategoryDTO>> getCategoryByName(@PathParam("name") String name){
        List<Category> categories = service.getCategoryByName(name);
        return ResponseEntity.ok(mapper.toCategoryDtos(categories));
    }

    @GetMapping("/getallusingjoin")
    public ResponseEntity<List<CategoryDTO>> getAllUsingJoin(){
        List<Category> categories = service.getAllUsingJoin();
        return ResponseEntity.ok(mapper.toCategoryDtos(categories));
    }
}
