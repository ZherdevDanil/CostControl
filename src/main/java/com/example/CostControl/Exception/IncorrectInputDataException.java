package com.example.CostControl.Exception;

import org.aspectj.bridge.Message;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncorrectInputDataException extends RuntimeException{
    public IncorrectInputDataException(String message){
        super("Error: not saved\nIncorrect input data : " + message);
    }

    public IncorrectInputDataException(){
        super("Error:Incorrect input data");
    }
}
