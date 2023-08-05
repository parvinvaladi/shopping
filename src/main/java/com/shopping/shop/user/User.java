package com.shopping.shop.user;

import com.shopping.shop.common.BaseEntity;
import com.shopping.shop.file_attachment.FileAttachment;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@Table(name = "tbl_user")
public class User extends BaseEntity {


    private String firstName;

    @NotNull(message = "lastname should not be null")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "gender should not be null")
    private Gender gender;

    @NotNull(message = "username should not be null")
    private String username;

    @NotNull(message = "password should not be null")
    private String password;

    @Email(message = "invalid email address")
    private String email;

    @NotBlank(message = "nationality should not be null")
    private String nationality;

    @NotNull(message = "national code should not be null")
    @Column(name = "national_code")
    private String nationalCode;

    @NotNull(message = "birth date should not be null")
    private Date birthDay;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "user",cascade = CascadeType.ALL)
    private FileAttachment fileAttachment;
}
