package com.ejercicio_crud.ejercicio_crud.exceptions;

public class NoProductFoundException extends RuntimeException{
    public NoProductFoundException(String msg){
        super(msg);
    }
}
