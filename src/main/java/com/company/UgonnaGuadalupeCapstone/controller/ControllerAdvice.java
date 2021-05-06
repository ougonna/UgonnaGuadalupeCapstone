package com.company.UgonnaGuadalupeCapstone.controller;

import com.company.UgonnaGuadalupeCapstone.model.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<CustomErrorResponse>> recordValidationError(MethodArgumentNotValidException e) {
        // BindingResult holds the validation errors
        BindingResult result = e.getBindingResult();
        // Validation errors are stored in FieldError objects
        List<FieldError> fieldErrors = result.getFieldErrors();

        // Translate the FieldErrors to CustomErrorResponse
        List<CustomErrorResponse> errorResponseList = new ArrayList<>();

        for (FieldError fieldError : fieldErrors) {
            CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.OK.toString(), fieldError.getDefaultMessage());
            errorResponse.setTimestamp(LocalDateTime.now());
            errorResponse.setStatus(HttpStatus.OK.value());
            errorResponseList.add(errorResponse);
        }
        // Create and return the ResponseEntity
        ResponseEntity<List<CustomErrorResponse>> responseEntity = new ResponseEntity<>(errorResponseList, HttpStatus.UNPROCESSABLE_ENTITY);
        return responseEntity;
    }



    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CustomErrorResponse> handleGenericNotFoundException(IllegalArgumentException e) {
        CustomErrorResponse error = new CustomErrorResponse(HttpStatus.OK.toString(), e.getMessage());
        error.setStatus((HttpStatus.OK.value()));
        error.setTimestamp(LocalDateTime.now());
        ResponseEntity<CustomErrorResponse> responseEntity = new ResponseEntity<>(error, HttpStatus.OK);
        return responseEntity;
    }

}

