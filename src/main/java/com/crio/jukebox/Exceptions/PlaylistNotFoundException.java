package com.crio.jukebox.Exceptions;


public class PlaylistNotFoundException extends RuntimeException{

    public PlaylistNotFoundException(){
        super();
    }

    public PlaylistNotFoundException(String mssg){
        super(mssg);
    }
}