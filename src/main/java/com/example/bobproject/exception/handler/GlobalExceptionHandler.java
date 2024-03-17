package com.example.bobproject.exception.handler;

import com.example.bobproject.exception.AlreadyExistsException;
import com.example.bobproject.exception.DuplicateUsernameException;
import com.example.bobproject.exception.EntityNotFoundException;
import com.example.bobproject.exception.InvalidBirthdateException;
import com.example.bobproject.model.ExceptionDTO;
import com.example.bobproject.model.responseDTO.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDTO handleNotFound(EntityNotFoundException entityNotFoundException){
        log.error("ActionLog.error not found: {} ", entityNotFoundException.getMessage());
        return new ExceptionDTO(HttpStatus.NOT_FOUND.value(), entityNotFoundException.getMessage());
    }

    @ExceptionHandler(InvalidBirthdateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDTO handleInvalidBirthdate(InvalidBirthdateException invalidBirthdateException){
        log.error("ActionLog.error invalid date for birthdate: {} ", invalidBirthdateException.getMessage());
        return new ExceptionDTO(HttpStatus.BAD_REQUEST.value(), invalidBirthdateException.getMessage());
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDTO handleConstraintViolation(ConstraintViolationException e){
        log.error("ActionLog.error ConstraintViolation: {} ", e.getMessage());
        return new ExceptionDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionDTO handleAuthentication(AuthenticationException e){
        log.error("ActionLog.error Authentication: {} ", e.getMessage());
        return new ExceptionDTO(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
    }

    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionDTO handleAlreadyExists(AlreadyExistsException e){
        log.error("ActionLog.error AlreadyExists: {} ", e.getMessage());
        return new ExceptionDTO(HttpStatus.CONFLICT.value(), e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ExceptionDTO handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        log.error("ActionLog.error HttpMessageNotReadable: Invalid JSON data: {} ", ex.getMessage());
        return new ExceptionDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("ActionLog.error MethodArgumentNotValidException: {} ", ex.getMessage());
        return new ExceptionDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler({DuplicateUsernameException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleDuplicateUsername(DuplicateUsernameException e){
        return ErrorResponse.builder()
                .message(e.getMessage())
                .build();
    }
}