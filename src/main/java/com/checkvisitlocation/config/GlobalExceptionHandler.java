package com.checkvisitlocation.config;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.JsonMappingException;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Глобальний обробник винятків для REST API.
 * Обробляє різні типи винятків та повертає відповідні HTTP відповіді.
 * 
 * @author CheckVisitLocation Team
 * @version 1.0
 * @since 2025
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private final MessageSource messageSource;

    /**
     * Створює новий екземпляр глобального обробника винятків.
     * 
     * @param messageSource джерело повідомлень для локалізації
     */
    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Обробляє винятки валідації аргументів методу.
     * 
     * @param ex виняток валідації
     * @param locale поточна локаль
     * @return відповідь з помилками валідації
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex, Locale locale) {
        Map<String, Object> errors = new HashMap<>();
        errors.put("status", HttpStatus.BAD_REQUEST.value());
        errors.put("error", "Validation Failed");

        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            String message = messageSource.getMessage(error.getDefaultMessage(), null, error.getDefaultMessage(), locale);
            fieldErrors.put(error.getField(), message);
        }
        errors.put("errors", fieldErrors);

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Обробляє винятки маппінгу JSON.
     * 
     * @param ex виняток маппінгу JSON
     * @param locale поточна локаль
     * @return відповідь з помилкою маппінгу
     */
    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<Map<String, Object>> handleJsonMappingException(JsonMappingException ex, Locale locale) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error", "Invalid Request Format");
        errorResponse.put("message", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Обробляє винятки некоректних аргументів.
     * 
     * @param ex виняток некоректного аргументу
     * @param locale поточна локаль
     * @return відповідь з помилкою аргументу
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex, Locale locale) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error", "Invalid Request");
        String message = messageSource.getMessage(ex.getMessage(), null, ex.getMessage(), locale);
        errorResponse.put("message", message);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Обробляє винятки некоректних облікових даних.
     * 
     * @param ex виняток некоректних облікових даних
     * @param locale поточна локаль
     * @return відповідь з помилкою автентифікації
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, Object>> handleBadCredentialsException(BadCredentialsException ex, Locale locale) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", HttpStatus.UNAUTHORIZED.value());
        errorResponse.put("error", "Unauthorized");
        errorResponse.put("message", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Обробляє загальні винятки.
     * 
     * @param ex загальний виняток
     * @param locale поточна локаль
     * @return відповідь з помилкою сервера
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex, Locale locale) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.put("error", "Internal Server Error");
        errorResponse.put("message", "An error occurred: " + ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}