package com.shopping.shop.category;

import com.shopping.shop.product.IProductService;
import com.shopping.shop.product.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/v1/category")
@Slf4j
public class CategoryController {

    private final ICategoryService service;

    @PostMapping("/")
    public ResponseEntity<Void> save(@RequestBody Category category){
        service.save(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Category>> getAll(){
        List<Category> categories = service.getAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id){
        Category category = service.getById(id);
        return ResponseEntity.ok(category);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category){
        Category category1 = service.update(id,category);
        return ResponseEntity.ok(category1);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteById(@PathParam("id") Long id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/categorypage")
    public ResponseEntity<List<Category>> pagingCategory(@PathParam("page") int page, @PathParam("size") int size){
        List<Category> categories = service.findAll(page,size);
        return ResponseEntity.ok(categories);
    }

}
