package com.example.cdr.exception;

public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(Long id) { super("Could not find record with id = " + id); }
}
