package pl.guitarshop.exceptions;

public class GuitarAlreadyExistsException extends RuntimeException {
    public GuitarAlreadyExistsException(String message) {
        super(message);
    }
}
