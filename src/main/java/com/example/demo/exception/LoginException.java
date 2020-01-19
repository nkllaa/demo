package com.example.demo.exception;

public class LoginException extends RuntimeException{
    public LoginException(){
        super();
    }
    public LoginException(String msg){
        super(msg);
    }
}
