package com.eduardo.tcgstore.exception;
/**
 * Custom runtime exception used for business rule violations.
 *
 * It is thrown when an operation breaks a functional rule,
 * such as insufficient stock, invalid order state,
 * duplicate username, or invalid input data.
 *
 * This helps separate technical errors from domain logic errors.
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
