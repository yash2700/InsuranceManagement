package com.insuranceManagement.InsuranceManagement.Exceptions;

public class InvalidInsuranceAmountException extends RuntimeException{
    public InvalidInsuranceAmountException(String message){
        super(message);
    }
}
