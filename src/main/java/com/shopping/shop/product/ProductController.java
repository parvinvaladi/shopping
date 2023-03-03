package com.shopping.shop.product;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/v1/product")
@AllArgsConstructor
@Slf4j
public class ProductController {

    private final IProductService service;

    private ProductMapper mapper;

    @PostMapping("/")
    public ResponseEntity<Void> save(@RequestBody ProductDTO productDTO){
        Product product = mapper.toProduct(productDTO);
        service.save(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Product>> getAll(){
        List<Product> products = service.getAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id){
        Product optionalProduct = service.getById(id);
        return ResponseEntity.ok(optionalProduct);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        Product product = mapper.toProduct(productDTO);
        Product product1 = service.update(id,product);
        ProductDTO productDTO1 = mapper.toProductDto(product1);
        return ResponseEntity.ok(productDTO1);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteById(@PathParam("id") Long id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getproduct")
    public ResponseEntity<List<ProductDTO>> findByName(
            @RequestParam(required = false)String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size ){
        List<Product> products = service.findByName(Optional.ofNullable(name),page,size);
        List<ProductDTO> productDTOS = mapper.toProductDtos(products);
        return ResponseEntity.ok(productDTOS);
    }
}
