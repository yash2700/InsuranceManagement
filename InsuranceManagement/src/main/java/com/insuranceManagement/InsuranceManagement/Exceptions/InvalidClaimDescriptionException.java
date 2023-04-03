package com.insuranceManagement.InsuranceManagement.Exceptions;

public class InvalidClaimDescriptionException extends RuntimeException{
    public InvalidClaimDescriptionException(String message){
        super(message);
    }
}
