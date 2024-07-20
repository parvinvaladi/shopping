package com.shopping.shop.file_attachment;

import com.shopping.shop.user.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tbl_file_attachment")
@Data
public class FileAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String personalImage;

    @OneToOne
    @JoinColumn(name = "user_id",unique = true)
    private User user;
}
