package com.codingboot.Core.config;


import com.codingboot.Core.domain.response.ApiResponse;
import com.codingboot.Core.domain.response.ErrorResponse;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAccessDeny implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, final HttpServletResponse response, AccessDeniedException exc) throws IOException {
        ApiResponse.Payload errorResponse = new ErrorResponse(HttpStatus.FORBIDDEN.value(), "Permission Denied").getBody();
        response.getWriter().append(new Gson().toJson(errorResponse));
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }
}