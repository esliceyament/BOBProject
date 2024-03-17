package com.example.bobproject.exception;

public class InvalidBirthdateException extends RuntimeException {
    public InvalidBirthdateException(String message) {
        super(message);
    }
}