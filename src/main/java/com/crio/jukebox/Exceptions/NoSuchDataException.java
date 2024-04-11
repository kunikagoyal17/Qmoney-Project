package com.crio.jukebox.Exceptions;

public class NoSuchDataException extends RuntimeException{

    public NoSuchDataException(){
        super();
    }

    public NoSuchDataException(String mssg){
        super(mssg);
    }
}