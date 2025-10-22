package org.skypro.skyshop.exceptions;

public class NoSuchProductException extends RuntimeException {

    public NoSuchProductException() {
        super();
    }

    public NoSuchProductException(String s) {
        super(s);
    }
}
