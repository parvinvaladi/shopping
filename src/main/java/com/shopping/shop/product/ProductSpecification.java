package com.shopping.shop.product;

import com.shopping.shop.common.SearchCriteria;
import com.shopping.shop.common.SearchOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecification implements Specification<Product> {

    private List<SearchCriteria> criterias;

    public ProductSpecification(){
        this.criterias = new ArrayList<>();
    }

    public void add(SearchCriteria searchCriteria){
        criterias.add(searchCriteria);
    }
    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        for(SearchCriteria searchCriteria:criterias){
            if(searchCriteria.getOperation().equals(SearchOperation.GREATER_THAN)){
                predicates.add(criteriaBuilder.greaterThan(root.get(searchCriteria.getKey()),searchCriteria.getValue()));
            }else if(searchCriteria.getOperation().equals(SearchOperation.LESS_THAN)){
                predicates.add(criteriaBuilder.lessThan(root.get(searchCriteria.getKey()),searchCriteria.getValue()));
            }else if(searchCriteria.getOperation().equals(SearchOperation.EQUAL)) {
                predicates.add(criteriaBuilder.equal(root.get(searchCriteria.getKey()), searchCriteria.getValue()));
            }else if(searchCriteria.getOperation().equals(SearchOperation.IN)) {
                predicates.add(criteriaBuilder.in(root.get(searchCriteria.getKey())).value(searchCriteria.getValue()));
            }else if (searchCriteria.getOperation().equals(SearchOperation.NOT_IN)) {
                predicates.add(criteriaBuilder.not(root.get(searchCriteria.getKey())).in(searchCriteria.getValue()));
            }
        }
        return null;
    }
}
