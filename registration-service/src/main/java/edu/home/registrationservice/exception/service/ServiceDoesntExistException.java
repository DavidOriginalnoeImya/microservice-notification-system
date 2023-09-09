package edu.home.registrationservice.exception.service;

public class ServiceDoesntExistException extends RuntimeException {

    public ServiceDoesntExistException() {
        super("Service doesn't exist");
    }

}
