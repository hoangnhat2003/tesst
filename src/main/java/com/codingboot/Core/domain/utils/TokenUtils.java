package com.codingboot.Core.domain.utils;

import com.codingboot.Core.domain.response.TokenDTO;
import com.google.gson.Gson;
import org.apache.tomcat.util.codec.binary.Base64;


public class TokenUtils {

    public static TokenDTO parseToken(String token) {
        String[] value = token.split("\\.");
        String base64EncodedBody = value[1];
        Base64 base64Url = new Base64(true);
        String body = new String(base64Url.decode(base64EncodedBody));
        Gson gson = new Gson();
        return gson.fromJson(body, TokenDTO.class);
    }
}
