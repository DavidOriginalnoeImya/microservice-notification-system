package edu.home.registrationservice.exception;

public class ServiceAlreadyExistsException extends RuntimeException {

    public ServiceAlreadyExistsException() {
        super("Service with this name and address already exists");
    }
}
