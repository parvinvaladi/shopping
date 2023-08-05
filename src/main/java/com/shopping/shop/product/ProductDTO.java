package com.shopping.shop.product;

import com.shopping.shop.category.Category;
import com.shopping.shop.category.CategoryDTO;
import com.shopping.shop.common.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class ProductDTO extends BaseDTO {

    private String name;

    private String price;

    private String description;

    private CategoryDTO category;
}
