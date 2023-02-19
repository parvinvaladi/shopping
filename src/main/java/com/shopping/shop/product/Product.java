package com.shopping.shop.product;

import com.shopping.shop.category.Category;
import com.shopping.shop.common.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tbl_product")
@Data
public class Product extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private String price;

    @Column(name = "description")
    private String description;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;
}
