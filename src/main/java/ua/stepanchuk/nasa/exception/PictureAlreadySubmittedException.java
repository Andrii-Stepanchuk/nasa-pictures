package ua.stepanchuk.nasa.exception;

public class PictureAlreadySubmittedException extends RuntimeException {
    public PictureAlreadySubmittedException() {
        super("You have already submitted picture");
    }
}
