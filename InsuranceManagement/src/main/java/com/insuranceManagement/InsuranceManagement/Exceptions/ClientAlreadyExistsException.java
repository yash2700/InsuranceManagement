package com.insuranceManagement.InsuranceManagement.Exceptions;

public class ClientAlreadyExistsException extends RuntimeException{
    public ClientAlreadyExistsException(String message){
        super(message);
    }
}
