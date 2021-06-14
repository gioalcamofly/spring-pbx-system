package com.example.pbx.exception;

public class PbxNotFoundException extends RuntimeException {

    public PbxNotFoundException(Long id) { super("Could not find pbx with id = " + id); }
}
