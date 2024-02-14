package org.shatskii.cinemademo.exception;

import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(NoSuchCartException exception) {
        IncorrectData incorrectData = new IncorrectData();
        incorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<>(incorrectData, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(NoSuchTicketException exception) {
        IncorrectData incorrectData = new IncorrectData();
        incorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<>(incorrectData, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(PSQLException exception) {
        IncorrectData incorrectData = new IncorrectData();
        String message = exception.getMessage();
        if (message != null && message.contains("bookings_seat_id_key")) {
            incorrectData.setInfo("Выбранные места заняты");
            return new ResponseEntity<>(incorrectData, HttpStatus.BAD_REQUEST);
        } else {
            incorrectData.setInfo("Неизвестная ошибка");
            return new ResponseEntity<>(incorrectData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
