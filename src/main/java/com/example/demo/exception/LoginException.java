package com.example.demo.exception;

public class LoginException extends Exception{
    public LoginException(){
        super();
    }
    public LoginException(String msg){
        super(msg);
    }
}
