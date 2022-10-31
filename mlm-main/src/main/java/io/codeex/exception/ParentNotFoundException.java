package io.codeex.exception;

public class ParentNotFoundException extends RuntimeException{

    public ParentNotFoundException(String message) {
        super(message);
    }

    public ParentNotFoundException() {
        super(ExceptionMessages.PARENT_NOT_FOUND);
    }
}
