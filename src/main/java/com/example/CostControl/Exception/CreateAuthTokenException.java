package com.example.CostControl.Exception;

import lombok.Data;

import java.util.Date;

@Data
public class CreateAuthTokenException {
    private int status;
    private String message;
    private Date timestamp;

    public CreateAuthTokenException(int status,String message){
        this.status = status;
        this.message=message;
        this.timestamp = new Date();
    }
}
