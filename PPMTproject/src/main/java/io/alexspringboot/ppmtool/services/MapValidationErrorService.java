package io.alexspringboot.ppmtool.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;


/**
 * Post a clearer and more readable error information.
 *
 * Note:
 * Most of the errors handled by this class are led by the illegal inputs in control forms of the webpages,
 * like create/update form, register/login form, etc,
 * while most of those errors from the server-end are handled by [exceptions/CustomResponseEntityExceptionHandler.java].
 *
 * Note:
 * This is a the service-layer class.
 * It does not directly communicate with the front-end.
 */
@Service
public class MapValidationErrorService {

    public ResponseEntity<?> mapValidationService(BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                System.out.println(error.getField());
                System.out.println(error.getDefaultMessage());
                errorMap.put(error.getField(), error.getDefaultMessage());
            }

            return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
        }

        return null;
    }
}
