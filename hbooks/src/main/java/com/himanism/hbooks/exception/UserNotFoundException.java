// In src/main/java/com/himanism/hbooks/exception/UserNotFoundException.java
package com.himanism.hbooks.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // Or handle it globally with a ControllerAdvice
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
