package com.ems.sow.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class ErrorResponse {

    private String message;
    private String details;
    private HttpStatus status;

    public ErrorResponse(String message, String details, HttpStatus status) {
        this.message = message;
        this.details = details;
        this.status = status;
    }

}
