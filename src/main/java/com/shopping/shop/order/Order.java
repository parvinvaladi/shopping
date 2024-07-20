package com.shopping.shop.order;

import com.shopping.shop.common.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_order")
@Data
public class Order extends BaseEntity {
}
