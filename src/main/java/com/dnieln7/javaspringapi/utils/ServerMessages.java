package com.dnieln7.javaspringapi.utils;

public enum ServerMessages {
    DELETE_SUCCESS("Deleted entity with id: ")
    ;

    private final String message;

    ServerMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
