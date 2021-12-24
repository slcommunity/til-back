package com.example.tilproject.handler.exception;

public class DataNotFoundException extends IllegalArgumentException{
    public DataNotFoundException(){
        super();
    }

    public DataNotFoundException(String message){
        super(message);
    }


}
