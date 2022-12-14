package pl.teb.spring.domain.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AppExceptionCode {
    NO_SUCH_PERSON("There is no such person with passed ID", 400),
    PERSON_DELETED("Person already deleted", 400);

    private final String message;
    private final int status;

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public DomainException createException() {
        return new DomainException(this);
    }
}
