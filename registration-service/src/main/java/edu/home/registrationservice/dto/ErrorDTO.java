package edu.home.registrationservice.dto;

public class ErrorDTO {

    private String message;

    public ErrorDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public ErrorDTO setMessage(String message) {
        this.message = message;

        return this;
    }

}
