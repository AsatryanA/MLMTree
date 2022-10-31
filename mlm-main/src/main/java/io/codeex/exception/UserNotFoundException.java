package io.codeex.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException() {
        super(ExceptionMessages.USER_NOT_FOUND);
    }
}
