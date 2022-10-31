package io.codeex.exception;

public class CompannyNotFoundException extends RuntimeException{

    public CompannyNotFoundException(String message) {
        super(message);
    }

    public CompannyNotFoundException() {
        super(ExceptionMessages.COMPANY_NOT_FOUND);
    }
}
