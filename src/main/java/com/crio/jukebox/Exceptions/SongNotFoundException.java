package com.crio.jukebox.Exceptions;

public class SongNotFoundException extends RuntimeException{

    public SongNotFoundException(){
        super();
    }

    public SongNotFoundException(String mssg){
        super(mssg);
    }

}

//Given song id is not a part of the active playlist