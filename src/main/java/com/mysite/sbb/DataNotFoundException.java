package com.mysite.sbb;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
// DataNotFoundException은 RuntimeException을 상속하여 만들었다
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "entity not found")
public class DataNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public DataNotFoundException(String message) {
        super(message);
    }

}

