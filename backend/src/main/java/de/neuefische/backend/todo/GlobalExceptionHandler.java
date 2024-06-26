package de.neuefische.backend.todo;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handdeMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        FieldError error = ex.getFieldErrors().get(0);

        return "Attribute: " + error.getField() + " " + error.getDefaultMessage();
    }
}
