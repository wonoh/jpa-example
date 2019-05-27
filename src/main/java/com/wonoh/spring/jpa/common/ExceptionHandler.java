package com.wonoh.spring.jpa.common;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ExceptionHandler {


    //MethodArgumentNotValidException --> @valid 에서 검증 실패하는 경우 발생하는 익셉션


    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e){

        log.info(e.getMessage());
        final List<ErrorResponse.FieldError> fieldErrors = getFieldErrors(e.getBindingResult());

        return buildFieldErrors(ErrorCode.INPUT_VALUE_INVALID,fieldErrors);


    }

    private List<ErrorResponse.FieldError> getFieldErrors(BindingResult bindingResult){

        final List<FieldError> errors = bindingResult.getFieldErrors();

        return errors.parallelStream()
                .map(fieldError -> new ErrorResponse.FieldError(
                                                    fieldError.getField(),
                                                    String.valueOf(fieldError.getRejectedValue()),
                                                    fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

    }

    private ErrorResponse buildFieldErrors(ErrorCode errorCode,List<ErrorResponse.FieldError> errors){

        return new ErrorResponse(errorCode.getMessage(),
                                errorCode.getCode(),
                                errorCode.getStatus(),
                                errors);
    }



}
