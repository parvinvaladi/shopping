package com.shopping.shop.user;

import com.shopping.shop.common.BaseDTO;
import lombok.Data;

import java.util.Date;

@Data
public class UserDTO extends BaseDTO {

    private String firstName;
    private String lastName;
    private Gender gender;
    private String username;
    private String password;
    private String nationality;
    private String nationalCode;
    private Long birthDay;
}
