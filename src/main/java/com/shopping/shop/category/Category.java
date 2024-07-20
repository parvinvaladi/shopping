package com.shopping.shop.category;

import com.shopping.shop.common.BaseEntity;
import com.shopping.shop.product.Product;
import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_category")
@Data
@Audited
public class Category extends BaseEntity{

    @Column(name = "name")
    private String categoryName;


    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Product> products;
}
