package com.ama.bankingassistant.exception;

import com.anthropic.errors.AnthropicIoException;
import com.anthropic.errors.AnthropicServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AnthropicServiceException.class)
    public ResponseEntity<Map<String, Object>> handleAnthropicServiceException(AnthropicServiceException ex) {
        int status = ex.statusCode();
        return ResponseEntity.status(status)
                .body(Map.of("error", ex.getMessage(), "status", status));
    }

    @ExceptionHandler(AnthropicIoException.class)
    public ResponseEntity<Map<String, Object>> handleAnthropicIoException(AnthropicIoException ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Map.of("error", "Upstream service unavailable: " + ex.getMessage(),
                        "status", HttpStatus.SERVICE_UNAVAILABLE.value()));
    }
}
