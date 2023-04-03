package com.insuranceManagement.InsuranceManagement.Exceptions;

public class InvalidPolicyTypeException extends  RuntimeException{
    public InvalidPolicyTypeException(String message){
        super(message);
    }
}
