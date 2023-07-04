package com.vrj.mysite.exceptions;

public class UserFoundException extends Exception{

    public UserFoundException(){
        super("User already exists");
    }

    public UserFoundException(String mensaje){
        super(mensaje);
    }

}
