package com.shopping.shop.user;

import com.shopping.shop.product.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    void save(User user);
    User getById(Long id);
    List<User> getAll();
    User update(Long id ,User user);
    void delete(Long id);

}
