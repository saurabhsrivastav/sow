package com.ems.sow.globalexceptions;

import com.ems.sow.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Resource Not Found Exception
     *
     * @param ex
     * @return
     */

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> notFoundException(ResourceNotFoundException ex) {
        Map<String, Object> exception = new HashMap<>();
        exception.put("message", ex.getMessage());
        exception.put("success", false);
        exception.put("status", HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception);
    }

    /**
     * Global Exception Handler
     *
     * @param ex
     * @return
     */

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception ex) {
        Map<String, Object> exception = new HashMap<>();
        exception.put("message", ex.getMessage());
        exception.put("success", false);
        exception.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception);
    }
}
