package com.codingboot.Core.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateExceptionHandling {
    private int code;
    private String message;
    private Object data;
}
