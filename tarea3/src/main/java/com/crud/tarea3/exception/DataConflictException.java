package com.crud.tarea3.exception;

// Para 409 Conflict: Duplicados o eliminación con productos asociados
public class DataConflictException extends RuntimeException {
    public DataConflictException(String message) {
        super(message);
    }
}