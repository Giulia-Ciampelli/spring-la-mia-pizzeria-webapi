package org.lessons.pizzeria.webapi.pizzeria_webapi.exception;

public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(String message) {
        super(message);
    }
}
