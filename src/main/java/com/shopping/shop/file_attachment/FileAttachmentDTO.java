package com.shopping.shop.file_attachment;

import com.shopping.shop.user.User;
import com.shopping.shop.user.UserDTO;
import lombok.Data;

import javax.persistence.*;

@Data
public class FileAttachmentDTO {


    private Long id;

    private String personalImage;

    private UserDTO user;
}
