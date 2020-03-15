package com.simba.microservices.journeybookingapi;

public class JourneyNotFoundException extends RuntimeException {
    static final String MSG_JOURNEY_NOT_FOUND = "Journey %d not found";

    public JourneyNotFoundException(String message) {
        super(message);
    }
}
