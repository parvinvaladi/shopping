package com.shopping.shop.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product,Long> {

    @EntityGraph(attributePaths = {"category"})
    Page<Product> findAll(Pageable pageable);
    Page<Product> findByName(String name, Pageable pageable);
//    Page<Product> findAllByPriceBetween(String price1 , String price2);
}
