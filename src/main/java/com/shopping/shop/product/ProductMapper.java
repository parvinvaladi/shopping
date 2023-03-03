package com.shopping.shop.product;

import com.shopping.shop.category.CategoryMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",uses = {CategoryMapper.class})
public interface ProductMapper {

    ProductDTO toProductDto(Product product);
    Product toProduct(ProductDTO productDTO);
    List<ProductDTO> toProductDtos(List<Product> products);
}
