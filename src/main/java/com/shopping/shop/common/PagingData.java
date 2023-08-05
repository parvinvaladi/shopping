package com.shopping.shop.common;

import com.shopping.shop.product.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PagingData<T> {

    //the total number of pages
    private Integer totalPage;

    private Integer currentPage;

    //the content of the current page
    private List<T> data;

}
