package com.shopping.shop.user;

import com.shopping.shop.common.PagingData;
import com.shopping.shop.product.IProductService;
import com.shopping.shop.product.Product;
import com.shopping.shop.product.ProductDTO;
import com.shopping.shop.product.ProductMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/v1/user")
@AllArgsConstructor
@Slf4j
public class UserController {

    private final IUserService service;

    private UserMapper mapper;

    @PostMapping("/")
    public ResponseEntity<Void> save(@RequestBody UserDTO userDTO){
        User user = mapper.toUser(userDTO);
        service.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAll(){
        List<User> users = service.getAll();
        List<UserDTO> userDTOS = mapper.toUserDTOs(users);
        return ResponseEntity.ok(userDTOS);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id){
        User user = service.getById(id);
        UserDTO userDTO = mapper.toUserDTO(user);
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO userDTO){
        User user = mapper.toUser(userDTO);
        User updatedUser = service.update(id,user);
        UserDTO updatedUserDTO = mapper.toUserDTO(updatedUser);
        return ResponseEntity.ok(updatedUserDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteById(@PathParam("id") Long id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
