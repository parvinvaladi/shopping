package com.shopping.shop.category;

import com.shopping.shop.common.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class CategoryDTO extends BaseDTO{


    @ApiModelProperty(required = true)
    private String categoryName;

}
