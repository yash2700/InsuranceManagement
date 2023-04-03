package com.insuranceManagement.InsuranceManagement.Exceptions;

public class InvalidClaimNumberException extends RuntimeException{
    public InvalidClaimNumberException(String message){
        super(message);
    }
}
