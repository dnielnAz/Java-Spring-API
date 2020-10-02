package com.dnieln7.javaspringapi.utils.exception;

/**
 * Basic exception thrown when there´s an error
 *
 * @author dnieln7
 */
public class ResponseException extends RuntimeException {
    public ResponseException(String message) {
        super(message);
    }
}
