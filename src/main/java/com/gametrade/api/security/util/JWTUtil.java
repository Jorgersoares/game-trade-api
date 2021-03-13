package com.gametrade.api.security.util;

import com.auth0.jwt.JWT;
import com.gametrade.api.security.constants.SecurityConstants;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC256;

@Component
public class JWTUtil {


    public String getSubjectToken(String token){

        String subject = JWT
                .require(HMAC256("secret")).
                        build().
                        verify(token).
                        getSubject();
        return subject;
    }

    public static String generateToken(String subject) {
        //2 HOURS
        int seconds = 7200;
        return JWT.
                create().
                withSubject(subject).
                withExpiresAt(new Date(System.currentTimeMillis() + (seconds * 1000))).
                sign(HMAC256(SecurityConstants.SECRET));
    }



}
