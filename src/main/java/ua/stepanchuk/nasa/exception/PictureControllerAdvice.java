package ua.stepanchuk.nasa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.stepanchuk.nasa.dto.response.ErrorResponse;

import java.util.NoSuchElementException;

@ControllerAdvice
public class PictureControllerAdvice {

    @ExceptionHandler({
            IncorrectPictureException.class,
            InvalidUserDataException.class,
            PictureAlreadySubmittedException.class,
            NoSuchElementException.class
    })
    public ResponseEntity<?> handleExceptions(RuntimeException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(e.getMessage()));
    }
}
