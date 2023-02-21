package com.shopping.shop.product;

import com.shopping.shop.category.CategoryMapper;
import org.mapstruct.Mapper;

@Mapper(uses = {CategoryMapper.class})
public interface ProductMapper {

    ProductDTO toProductDto(Product product);
    Product toProduct(ProductDTO productDTO);
}
