package com.omarinhos.servlet.services;

public class ServiceJdbcException extends RuntimeException{

    public ServiceJdbcException(String mensaje) {
        super(mensaje);
    }

    public ServiceJdbcException(String message, Throwable cause) {
        super(message, cause);
    }
}
