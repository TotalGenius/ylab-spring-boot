package com.edu.ulab.app.dto.web.handler;

import com.edu.ulab.app.exception.*;
import com.edu.ulab.app.dto.web.response.BaseWebResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<BaseWebResponse> handleNotFoundExceptionException(@NonNull final NotFoundException exc) {
        log.error(exc.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new BaseWebResponse(createErrorMessage(exc)));
    }


    @ExceptionHandler(IncorrectUserIdException.class)
    public ResponseEntity<BaseWebResponse> handleIncorrectUserIdException(@NonNull final IncorrectUserIdException exc) {
        log.error(exc.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new BaseWebResponse(createErrorMessage(exc)));
    }


    @ExceptionHandler(NotFoundUserWithSuchIdException.class)
    public ResponseEntity<BaseWebResponse> handleNotFoundUserWithSuchIdException(@NonNull final NotFoundUserWithSuchIdException exc) {
        log.error(exc.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new BaseWebResponse(createErrorMessage(exc)));
    }

    @ExceptionHandler(BookAlreadyExistsException.class)
    public ResponseEntity<BaseWebResponse> handleBookAlreadyExistsException(@NonNull final BookAlreadyExistsException exc) {
        log.error(exc.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new BaseWebResponse(createErrorMessage(exc)));
    }

    @ExceptionHandler(NotFoundUserInRequestException.class)
    public ResponseEntity<BaseWebResponse> handleNotFoundUserInRequestException(@NonNull final NotFoundUserInRequestException exc) {
        log.error(exc.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new BaseWebResponse(createErrorMessage(exc)));
    }

    @ExceptionHandler(NotFoundBookWithSuchIdException.class)
    public ResponseEntity<BaseWebResponse> handleNotFoundBookWithSuchIdException(@NonNull final NotFoundBookWithSuchIdException exc) {
        log.error(exc.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new BaseWebResponse(createErrorMessage(exc)));
    }

    @ExceptionHandler(ValidException.class)
    public ResponseEntity<BaseWebResponse> handleValidException(@NonNull final ValidException exc) {
        log.error(exc.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new BaseWebResponse(createErrorMessage(exc)));
    }

    private String createErrorMessage(Exception exception) {
        final String message = exception.getMessage();
        log.error(ExceptionHandlerUtils.buildErrorMessage(exception));
        return message;
    }
}
