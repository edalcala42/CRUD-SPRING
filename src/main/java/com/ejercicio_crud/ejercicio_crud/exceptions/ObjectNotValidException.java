package com.ejercicio_crud.ejercicio_crud.exceptions;

import java.util.Set;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ObjectNotValidException extends RuntimeException{
    private final Set<String> errorMessages;
}
