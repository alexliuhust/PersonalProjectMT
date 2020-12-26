package io.alexspringboot.ppmtool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ProjectNotFound:
 * It accepts the customised exception message and packs that massage as an Exception Object
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectNotFound extends RuntimeException {

    public ProjectNotFound(String message) {
        super(message);
    }
}
