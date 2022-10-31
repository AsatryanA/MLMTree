package io.codeex.exception;

public class LevelNotDefinedException extends RuntimeException{
    public LevelNotDefinedException(String message) {
        super(message);
    }

    public LevelNotDefinedException() {
        super(ExceptionMessages.LEVEL_NOT_DEFINED);
    }
}
