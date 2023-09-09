package edu.home.registrationservice.exception.service;

public class ServiceAlreadyExistsException extends RuntimeException {

    public ServiceAlreadyExistsException() {
        super("Service with this name and address already exists");
    }
}
