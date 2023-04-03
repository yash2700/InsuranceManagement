package com.insuranceManagement.InsuranceManagement.ExceptionController;

import com.insuranceManagement.InsuranceManagement.Exceptions.*;
import com.insuranceManagement.InsuranceManagement.ResponseMessage.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @Autowired
    Environment environment;

    @ExceptionHandler({ClientNameException.class, ContactInformationException.class, DateException.class, ContactInformationException.class, AddressException.class, InsurancePolicyNumberException.class,ClientAlreadyExistsException.class, NullClientException.class, InsurancePolicyNumberException.class, InvalidInsuranceAmountException.class, InvalidClaimDescriptionException.class, InvalidClaimNumberException.class, NullClientException.class, InvalidPolicyTypeException.class})
    private ResponseEntity<ResponseMessage> handleExceptions(RuntimeException e){
        ResponseMessage response=new ResponseMessage();
        response.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
        response.setMessage(environment.getProperty(e.getMessage()));
        return  new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({IdNotFoundException.class})
    private ResponseEntity<ResponseMessage> handleExceptions1(RuntimeException e){
        ResponseMessage responseMessage=new ResponseMessage();
        responseMessage.setHttpStatus(HttpStatus.NOT_FOUND);
        responseMessage.setMessage(environment.getProperty(e.getMessage()));
        return new ResponseEntity<>(responseMessage,HttpStatus.NOT_FOUND);
    }

}
