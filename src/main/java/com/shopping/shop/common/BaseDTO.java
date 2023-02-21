package com.shopping.shop.common;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import java.util.Date;

@Data
public class BaseDTO {

    private Long id;

    private Integer version;

    private String createdBy;

    private String createdDate;

    private String lastModifiedBy;

    private Date lastModifiedDate;
}
