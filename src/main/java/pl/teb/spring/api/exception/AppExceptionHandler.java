package pl.teb.spring.api.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.teb.spring.domain.exception.DomainException;

import java.time.Instant;


@Slf4j
@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Error> handleDomainException(DomainException exception) {
        log.error("Domain exception occurred", exception);

        HttpStatus httpStatus = HttpStatus.valueOf(exception.getCode().getStatus());
        Error body = new Error(httpStatus.value(), exception.getMessage(), Instant.now());

        return new ResponseEntity<>(body, httpStatus);
    }

    public static class Error {
        private final int status;
        private final String message;
        private final Instant timestamp;

        private Error(int status, String message, Instant timestamp) {
            this.status = status;
            this.message = message;
            this.timestamp = timestamp;
        }

        public int getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }

        public Instant getTimestamp() {
            return timestamp;
        }
    }
}