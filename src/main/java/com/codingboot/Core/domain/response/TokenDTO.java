package com.codingboot.Core.domain.response;

import com.codingboot.Core.domain.entity.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {
    private Long userId;
    private String username;
    private String email;
    private String accessToken;
    private String refreshToken;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date expiredDate;
    private Set<Role> roles;
}
