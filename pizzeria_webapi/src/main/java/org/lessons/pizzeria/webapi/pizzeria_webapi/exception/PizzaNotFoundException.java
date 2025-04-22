package org.lessons.pizzeria.webapi.pizzeria_webapi.exception;

public class PizzaNotFoundException extends RuntimeException {
    public PizzaNotFoundException(String message) {
        super(message);
    }
}
