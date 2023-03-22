package com.shopping.shop.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category,Long> {

    @EntityGraph(attributePaths = {"products"})
    Page<Category> findAll(Pageable pageable);

    //exercise JPQL & native query
    @Query("SELECT c FROM Category c")
    Collection<Category> getAllCategoriesJPQL();

    @Query("SELECT c FROM Category c")
    List<Category> getAllCategoriesSort(Sort sort);

    @Query(value = "SELECT * FROM tbl_category c",nativeQuery = true)
    List<Category> getAllCategoriesNative();

    @Query("SELECT COUNT(c) FROM Category c")
    Integer getCountOfCategory();

    //named parameters
    @Query("SELECT c FROM Category c WHERE c.categoryName = :name")
    List<Category> getCategoryByName(@Param("name") String name);

    //left join
    @Query("SELECT c FROM Category c LEFT JOIN c.products p")
    List<Category> getAllUsingJoin();
}
