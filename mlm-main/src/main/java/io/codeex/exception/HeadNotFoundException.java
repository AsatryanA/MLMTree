package io.codeex.exception;

public class HeadNotFoundException extends RuntimeException{

    public HeadNotFoundException(String message) {
        super(message);
    }

    public HeadNotFoundException() {
        super(ExceptionMessages.HEAD_NOT_FOUND);
    }
}
