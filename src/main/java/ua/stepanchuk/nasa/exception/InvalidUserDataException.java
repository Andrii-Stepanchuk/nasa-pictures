package ua.stepanchuk.nasa.exception;

public class InvalidUserDataException extends RuntimeException {

    public InvalidUserDataException() {
        super("Incorrect user data");
    }
}
