package com.test.Exceptions;

public class BaseException extends Exception {
    public BaseException(String message) {
        super(message);
        System.out.println(message);
    }
}
