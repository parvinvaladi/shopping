package com.shopping.shop.product;

import com.shopping.shop.category.Category;
import com.shopping.shop.common.BaseEntity;
import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "tbl_product")
@Data
@Audited
public class Product extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private String price;

    @Column(name = "description")
    private String description;

    @ManyToOne()
    @JoinColumn(name = "prodict_category_fk")
    private Category category;
}
