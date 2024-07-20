package com.shopping.shop.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
@AllArgsConstructor
public class ApiError {

    private HttpStatus status;
    private String message;
}
