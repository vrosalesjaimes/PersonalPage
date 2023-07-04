package com.vrj.mysite.exceptions;

public class UserNotFoundException extends Exception{

    public UserNotFoundException(){
        super("User not found");
    }

    public UserNotFoundException(String mensaje){
        super(mensaje);
    }

}
