package com.shopping.shop.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {

    @EntityGraph(attributePaths = {"category"})
    Page<Product> findAll(Pageable pageable);
    Page<Product> findAllByCategory_Id(Long id,Pageable pageable);
    Page<Product> findByName(String name, Pageable pageable);
//    Page<Product> findAllByPriceBetween(String price1 , String price2);

    //exercise JPQL & native query
    @Query(value = "SELECT p FROM Product p ORDER BY id")
    List<Product> geAllProductOrdered();

    @Query(value = "SELECT p FROM Product p WHERE p.name = 'product1'")
    List<Product> getProduct1();

    // indexed query parameters
    @Query(value = "SELECT p FROM Product p WHERE p.name = ?1")
    List<Product> getProductByName(String name);

    @Query("SELECT p FROM Product p LEFT JOIN p.category c")
    List<Product> getAllUsingJoin();

    @Query("SELECT p FROM Product p JOIN p.category c WHERE c.categoryName = :name")
    List<Product> getProductOfACategory(@Param("name") String name);

    Page<Product> findAll(Specification<Product> specification,Pageable pageable);
}
