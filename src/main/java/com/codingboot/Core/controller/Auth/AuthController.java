package com.codingboot.Core.controller.Auth;

import com.codingboot.Core.config.jwt.JwtUtils;
import com.codingboot.Core.domain.entity.RefreshToken;
import com.codingboot.Core.domain.entity.User;
import com.codingboot.Core.domain.exception.AuthExceptionHandling;
import com.codingboot.Core.domain.exception.TokenRefreshException;
import com.codingboot.Core.domain.request.LoginRequest;
import com.codingboot.Core.domain.request.RefreshTokenRequest;
import com.codingboot.Core.domain.response.ApiResponse;
import com.codingboot.Core.domain.response.TokenDTO;
import com.codingboot.Core.domain.response.TokenRefreshResponse;
import com.codingboot.Core.service.Auth.RefreshTokenService;
import com.codingboot.Core.service.Auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;

@RequestMapping("/api/v1")
@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private JwtUtils jwtUtil;

    @PostMapping("/authenticate")
    public ApiResponse authenticate(@Valid @RequestBody LoginRequest request) throws Exception {
        User userDetails = userService.findByEmail(request.getEmail());
        authenticate(request.getEmail(), request.getPassword());

        final String token = jwtUtil.generateToken(userDetails);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setUserId(userDetails.getId());
        tokenDTO.setEmail(request.getEmail());
        tokenDTO.setUsername(userDetails.getUsername());
        tokenDTO.setAccessToken(token);
        tokenDTO.setRoles(userDetails.getRoles());
        tokenDTO.setRefreshToken(refreshToken.getToken());
        tokenDTO.setExpiredDate(jwtUtil.getExpirationDateFromToken(token));

        return new ApiResponse(HttpStatus.OK.value(), "Login successfully", tokenDTO);
    }

    private void authenticate(String email, String password) throws Exception {
        Objects.requireNonNull(email);
        Objects.requireNonNull(password);
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new AuthExceptionHandling(HttpStatus.BAD_REQUEST.value(), "Password is incorrect", null);
        } catch (AuthenticationException e) {
            throw new AuthExceptionHandling(HttpStatus.BAD_REQUEST.value(), "Email has not been registered", null);
        }
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        String requestRefreshToken = request.getRefreshToken();
        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtil.generateToken(user);
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(HttpStatus.BAD_REQUEST.value(),"Refresh token not found in database", requestRefreshToken));
    }
}
