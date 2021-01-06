package io.alexspringboot.ppmtool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsernameDuplicate extends RuntimeException{

    public UsernameDuplicate(String message) {
        super(message);
    }
}
