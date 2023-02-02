package solo.board.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import solo.board.exception.NotFoundException;
import solo.board.exception.api.RestApiException;
import solo.board.exception.api.Status;
@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestApiException NotFoundException(NotFoundException e){
        log.error("e = {}", e.getStatus().getMessage());
        return new RestApiException(e.getStatus());
    }
}
