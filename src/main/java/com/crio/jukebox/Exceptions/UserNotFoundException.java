package com.crio.jukebox.Exceptions;


public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(){
        super();
    }

    public UserNotFoundException(String mssg){
        super(mssg);
    }
}