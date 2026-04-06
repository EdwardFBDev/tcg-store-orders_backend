package com.eduardo.tcgstore.exception;
// me ayudara a cambiar los mensajes de salida
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
