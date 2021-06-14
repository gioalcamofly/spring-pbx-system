package com.example.phonebook.exceptions;

public class ContactNotFoundException extends RuntimeException {

    public ContactNotFoundException(String column, Object value) { super("Could not find contact user with " + column + " " + value); }
}
