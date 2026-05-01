package com.algotracker.AlgotrackerProject.exceptions;

public class TopicInUseException extends RuntimeException {
    public TopicInUseException(String message) {
        super(message);
    }
}
