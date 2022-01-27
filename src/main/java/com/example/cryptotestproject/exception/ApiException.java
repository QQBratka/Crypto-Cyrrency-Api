package com.example.cryptotestproject.exception;

import java.time.ZonedDateTime;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiException {
    private final String message;
    private final Throwable throwable;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;
}
