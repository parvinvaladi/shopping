package com.shopping.shop.product;

import com.shopping.shop.category.Category;
import com.shopping.shop.category.ICategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService implements IProductService{

    private final ProductRepository repository;
    private final ICategoryService categoryService;

    @Override
    public void save(Product product) {
        Long categoryId = product.getCategory().getId();
        Category category = categoryService.getById(categoryId);
        product.setCategory(category);
        log.info(String.valueOf(product.getCategory()));
         repository.save(product);
    }

    @Override
    public Product getById(Long id) {
        Optional<Product> optionalProduct = repository.findById(id);
        return optionalProduct.get();
    }

    @Override
    public List<Product> getAll() {
        List<Product> list = (List<Product>) repository.findAll();
        return list;
    }

    @Override
    public Product update(Long id ,Product product) {
        Product product1;
        product1 = getById(id);
        product1.setName(product.getName());
        product1.setPrice(product.getPrice());
        product1.setDescription(product.getDescription());
        save(product1);
        return product1;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Product> findByName(Optional<String> name, int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Product> productPage;
        if(name.isEmpty()){
            productPage = repository.findAll(pageable);
        }else {
            productPage = repository.findByName(name.get(),pageable);
        }
        List<Product> productList = productPage.getContent();
        return productList;
    }

}
