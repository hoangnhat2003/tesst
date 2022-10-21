package com.codingboot.Core.domain.handler;

import com.codingboot.Core.domain.exception.AuthExceptionHandling;
import com.codingboot.Core.domain.exception.ServiceException;
import com.codingboot.Core.domain.exception.TokenRefreshException;
import com.codingboot.Core.domain.exception.ValidateExceptionHandling;
import com.codingboot.Core.domain.response.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status,
            final WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        HttpHeaders header = new HttpHeaders();
        ObjectMapper mapper = new ObjectMapper();
        Object object = mapper.convertValue(errors, Object.class);
        ValidateExceptionHandling apiError = new ValidateExceptionHandling(HttpStatus.BAD_REQUEST.value(), "Invalid Parameters", object);
        return new ResponseEntity<>(apiError, header, HttpStatus.OK);
    }

    @ExceptionHandler({ServiceException.class})
    public ApiResponse handleServiceException(HttpServletRequest req, HttpServletResponse res,
                                              final ServiceException ex) throws IOException {
        ApiResponse apiError = new ApiResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null);
        return apiError;
    }

    @ExceptionHandler({AuthExceptionHandling.class})
    public ApiResponse handleAuthException(HttpServletRequest req, HttpServletResponse res,
                                              final AuthExceptionHandling ex) throws IOException {
        ApiResponse apiError = new ApiResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null);
        return apiError;
    }

    @ExceptionHandler({TokenRefreshException.class})
    public ApiResponse handleTokenRefreshException(HttpServletRequest req, HttpServletResponse res,
                                           final TokenRefreshException ex) throws IOException {
        ApiResponse apiError = new ApiResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), ex.getData());
        return apiError;
    }
}
