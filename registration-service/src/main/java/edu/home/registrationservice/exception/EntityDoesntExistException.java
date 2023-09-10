package edu.home.registrationservice.exception;

public class EntityDoesntExistException extends RuntimeException {

    public EntityDoesntExistException(String message) {
        super(message);
    }
}
