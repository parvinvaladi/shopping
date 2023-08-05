package com.shopping.shop.product;

import com.shopping.shop.common.PagingData;
import com.shopping.shop.common.SearchCriteria;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Collection;
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

    @GetMapping("/")
    public ResponseEntity<List<ProductDTO>> getAll(){
        List<Product> products = service.getAll();
        List<ProductDTO> productDTOS = mapper.toProductDtos(products);
        return ResponseEntity.ok(productDTOS);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Long id){
        Product optionalProduct = service.getById(id);
        ProductDTO productDTO = mapper.toProductDto(optionalProduct);
        return ResponseEntity.ok(productDTO);
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

    @GetMapping("/findbycategoryid")
    public ResponseEntity<PagingData<ProductDTO>> findAllByCategoryId(
            @RequestParam Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size){
        Page<Product> products = service.findByCategoryId(id,page,size);
        Integer totalPage = products.getTotalPages();
        List<Product> productList = products.getContent();
        List<ProductDTO> productDTOS = mapper.toProductDtos(productList);
        PagingData<ProductDTO> pagingData = new PagingData<>(totalPage,page,productDTOS);
        return ResponseEntity.ok(pagingData);
    }

    @GetMapping(value = "/findproductbyname")
    public ResponseEntity<PagingData<ProductDTO>> findByName(
            @RequestParam(required = false)String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size ){
        Page<Product> productPage = service.findByName(Optional.ofNullable(name),page,size);
        Integer totalPage = productPage.getTotalPages();
        List<Product> productList = productPage.getContent();
        List<ProductDTO> productDTOS = mapper.toProductDtos(productList);
        PagingData<ProductDTO> pagingData = new PagingData<>(totalPage,page,productDTOS);
        return ResponseEntity.ok(pagingData);
    }

    @GetMapping("/getallproductordered")
    public ResponseEntity<List<ProductDTO>> getSomeProduct(){
        List<Product> products = service.geAllProductOrdered();
        List<ProductDTO> productDTOS = mapper.toProductDtos(products);
        return ResponseEntity.ok(productDTOS);
    }

    @GetMapping("/getproduct1")
    public ResponseEntity<List<ProductDTO>> getProduct1(){
        List<Product> products = service.getProduct1();
        List<ProductDTO> productDTOS = mapper.toProductDtos(products);
        return ResponseEntity.ok(productDTOS);
    }

    @GetMapping("/getproductbyname")
    public ResponseEntity<List<ProductDTO>> getProductByName(@PathParam("name") String name){
        List<Product> products = service.getProductByName(name);
        List<ProductDTO> productDTOS = mapper.toProductDtos(products);
        return new ResponseEntity<>(productDTOS,HttpStatus.OK);
    }

    @GetMapping("/getallusingjoin")
    public ResponseEntity<List<ProductDTO>> getAllUsingJoin(){
        List<Product> products = service.getAllUsingJoin();
        return ResponseEntity.ok(mapper.toProductDtos(products));
    }

    @GetMapping("/productofacategory")
    public ResponseEntity<List<ProductDTO>> getProductOfACategory(@PathParam("name") String name){
        List<Product> products = service.getProductOfACategory(name);
        return ResponseEntity.ok(mapper.toProductDtos(products));
    }

    @PostMapping("/search")
    public ResponseEntity<List<ProductDTO>> search(@RequestBody List<SearchCriteria> searchCriterias){
        List<Product> products = service.search(searchCriterias);
        List<ProductDTO> productDTOS = mapper.toProductDtos(products);
        return ResponseEntity.ok(productDTOS);
    }
}
