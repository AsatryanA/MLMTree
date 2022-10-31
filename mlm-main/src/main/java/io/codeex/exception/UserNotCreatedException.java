package io.codeex.exception;

public class UserNotCreatedException extends RuntimeException{

    public UserNotCreatedException(String message) {
        super(message);
    }

    public UserNotCreatedException() {
        super(ExceptionMessages.BUSINESS_NOT_FOUND);
    }
}
