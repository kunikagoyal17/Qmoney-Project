package com.crio.jukebox.Exceptions;

    
public class EmptyPlaylistException extends RuntimeException{

    public EmptyPlaylistException(){
        super();
    }

    public EmptyPlaylistException(String mssg){
        super(mssg);
    }

    
}
