package com.example.hausaufgabe6.Exceptions;

/**
 * InputException class extends Exception
 * used for wrong parameters in the RegistrationSystem
 */
public class InputException extends Exception {
    public InputException(String s) {
        super(s);
    }
}
