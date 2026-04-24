package com.algotracker.AlgotrackerProject.exceptions;

import com.algotracker.AlgotrackerProject.common.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Object>> handleUserExists(UserAlreadyExistsException exception) {
        ApiResponse<Object> apiResponse = new ApiResponse<>(false, exception.getMessage(), null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleInvalidArgs(
            MethodArgumentNotValidException exception) {
        Map<String, String> errorMap = new HashMap<>();

        exception.getBindingResult().getFieldErrors()
                .forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
        ApiResponse<Map<String, String>> apiResponse =
                new ApiResponse<>(false, "Validation Failed", errorMap);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    //same as MethodArgumentNotValidException validation using non-null and nonempty annotation, but will check while
    // doing db
    // validation.
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<Object>> handleDataIntegrity(DataIntegrityViolationException exception) {
        ApiResponse<Object> apiResponse = new ApiResponse<>(false, "Duplicate data found", null);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleUserNotFound(UserNotFoundException exception) {
        ApiResponse<Object> apiResponse = new ApiResponse<>(false, exception.getMessage(), null);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    //Validating path variables, request params, method-level constraints
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Object>> handleConstraints(ConstraintViolationException exception) {
        String message = exception.getConstraintViolations().iterator().next().getMessage();
        ApiResponse<Object> response = new ApiResponse<>(false, message, null);

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ProblemNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleProblemNotFound(ProblemNotFoundException exception) {
        ApiResponse<Object> apiResponse = new ApiResponse<>(false, exception.getMessage(), null);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(UserProblemAlreadyExists.class)
    public ResponseEntity<ApiResponse<Object>> handleUserProblemExists(UserProblemAlreadyExists ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiResponse<>(false, ex.getMessage(), null));
    }

}
