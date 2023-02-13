package com.shopping.shop.product;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService implements IProductService{

    private final ProductRepository repository;

    @Override
    public Product save(Product product) {
        return repository.save(product);
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
}
