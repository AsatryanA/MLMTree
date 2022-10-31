package io.codeex.exception;

public class BusinessNotFoundException extends RuntimeException{

    public BusinessNotFoundException(String message) {
        super(message);
    }

    public BusinessNotFoundException() {
        super(ExceptionMessages.BUSINESS_NOT_FOUND);
    }
}
