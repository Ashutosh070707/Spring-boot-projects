package com.example.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
    // 1. Handle "Duplicate Entry" errors (from Database constraints)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleConflict(DataIntegrityViolationException ex) {
        return new ResponseEntity<>("Error: Duplicate entry detected (Username, Email, or Name already exists).",
                HttpStatus.CONFLICT);
    }

    // 2. Handle "Validation" errors (from your Service layer logic)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequest(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // You can also handle the generic RuntimeException if your service throws that
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // Handle "Validation Failed" (e.g. Missing username, Invalid email)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        // Loop through errors: "email" -> "Email must be valid"
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // 3. Handle "Everything Else" (NullPointer, Database Down, etc.)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralError(Exception ex) {
        // In production, use a Logger here instead of printing to console!
        // Logger.error("Unexpected error", ex);
        ex.printStackTrace();

        return new ResponseEntity<>("An internal server error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
