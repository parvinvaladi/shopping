package com.shopping.shop.category;

import com.shopping.shop.common.BaseEntity;
import com.shopping.shop.product.Product;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_category")
public class Category extends BaseEntity {

    @Column(name = "name")
    private String categoryName;

    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
    private List<Product> products;
}
