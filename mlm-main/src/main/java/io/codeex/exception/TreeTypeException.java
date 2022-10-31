package io.codeex.exception;

public class TreeTypeException extends RuntimeException{

    public TreeTypeException(String message) {
        super(message);
    }

    public TreeTypeException() {
        super(ExceptionMessages.TREE_TYPE_NOT_DEFINED);
    }
}
