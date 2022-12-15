package pl.teb.spring.api.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.teb.spring.domain.exception.DomainException;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Error> handleDomainException(DomainException exception) {
        log.error("Domain exception occurred", exception);

        HttpStatus httpStatus = HttpStatus.valueOf(exception.getCode().getStatus());
        Error body = new Error(httpStatus.value(), exception.getMessage());

        return new ResponseEntity<>(body, httpStatus);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> defaultHandler(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(new Error(400,
                "Request validation failed",
                e.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList())),
                HttpStatus.BAD_REQUEST);
    }

    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    @Getter
    public static class Error {
        int status;
        String message;
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        List<String> details;
        Instant timestamp = Instant.now();

        public Error(int status, String message) {
            this.status = status;
            this.message = message;
            details = null;
        }

        public Error(int status, String message, List<String> details) {
            this.status = status;
            this.message = message;
            this.details = details;
        }
    }
}