package ru.bellst.authorization.security;

public class InvalidJwtAuthenticationException extends RuntimeException {
    public InvalidJwtAuthenticationException(String expiredOrInvalidJwtToken) {
        super(expiredOrInvalidJwtToken);
    }
}
