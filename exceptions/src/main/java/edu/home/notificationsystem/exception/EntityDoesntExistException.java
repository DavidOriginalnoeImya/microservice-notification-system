package edu.home.notificationsystem.exception;

public class EntityDoesntExistException extends RuntimeException {

    public EntityDoesntExistException(String message) {
        super(message);
    }
}
