package ua.stepanchuk.nasa.exception;

public class IncorrectPictureException extends RuntimeException {
    public IncorrectPictureException() {
        super("Your picture isn't Nasa Rover picture");
    }
}
