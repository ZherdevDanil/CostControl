package com.example.CostControl.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundRecordsException extends RuntimeException {
    public NotFoundRecordsException(Long userId, Long categoryId){
        super("Records not found by userId=" + userId + " and categoryId =" + categoryId);
    }

    public NotFoundRecordsException(String message,Long Id){
        super("Records not found by " + message +"=" + Id);
    }
}
