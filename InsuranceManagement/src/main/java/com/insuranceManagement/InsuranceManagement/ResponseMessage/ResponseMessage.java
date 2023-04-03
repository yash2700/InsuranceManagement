package com.insuranceManagement.InsuranceManagement.ResponseMessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseMessage {
    private HttpStatus httpStatus;
    private String message;


}
