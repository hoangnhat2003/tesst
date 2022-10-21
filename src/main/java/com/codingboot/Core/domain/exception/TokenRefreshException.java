package com.codingboot.Core.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TokenRefreshException extends RuntimeException {
    private int code;
    private String message;
    private Object data;
}
