package io.codeex.exception;

public class IdNotFoundException extends RuntimeException{

    public IdNotFoundException(String message) {
        super(message);
    }

    public IdNotFoundException() {
        super(ExceptionMessages.ID_NOT_FOUND);
    }
}
