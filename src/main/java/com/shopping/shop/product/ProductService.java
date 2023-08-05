package com.shopping.shop.product;

import com.shopping.shop.category.Category;
import com.shopping.shop.category.ICategoryService;
import com.shopping.shop.common.SearchCriteria;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
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
         repository.save(product);
    }

    @Override
    public Product getById(Long id) {
        Optional<Product> optionalProduct = repository.findById(id);
        if(!optionalProduct.isPresent()){
            throw new NotFoundException("not found");
        }
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
    public Page<Product> findByCategoryId(Long id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = repository.findAllByCategory_Id(id,pageable);
        return products;
    }

    @Override
    public Page<Product> findByName(Optional<String> name, int page, int size) {
        Pageable pageable = PageRequest.of(page,size, Sort.by("id").ascending());
        Page<Product> productPage;
        if(name.isEmpty()){
            productPage = repository.findAll(pageable);
        }else {
            productPage = repository.findByName(name.get(),pageable);
        }
//        List<Product> productList = productPage.getContent();
        return productPage;
    }

    @Override
    public List<Product> geAllProductOrdered() {
        return repository.geAllProductOrdered();
    }

    @Override
    public List<Product> getProduct1() {
        List<Product> products = repository.getProduct1();
        return products;
    }

    @Override
    public List<Product> getProductByName(String name) {
        return repository.getProductByName(name);
    }

    @Override
    public List<Product> getAllUsingJoin() {
        return repository.getAllUsingJoin();
    }

    @Override
    public List<Product> getProductOfACategory(String name) {
        return repository.getProductOfACategory(name);
    }

    @Override
    public List<Product> search(List<SearchCriteria> criteriaList) {
        ProductSpecification specification = new ProductSpecification();
        criteriaList.forEach(criteria -> specification.add(criteria));
        return repository.findAll(specification);
    }
}
