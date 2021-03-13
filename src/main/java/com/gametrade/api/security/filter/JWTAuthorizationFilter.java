package com.gametrade.api.security.filter;

import com.gametrade.api.security.constants.SecurityConstants;
import com.gametrade.api.security.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        final String token = req.getHeader(SecurityConstants.HEADER_STRING);

        if(token != null && token.startsWith(SecurityConstants.TOKEN_PREFIX)){
            String userUsername = null;
            try {
                userUsername = jwtUtil.getSubjectToken(token.replace(SecurityConstants.TOKEN_PREFIX, ""));
            } catch (Exception e) {
               new Exception(e.getMessage());
            }
            if(userUsername != null){
                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(userUsername, null, new ArrayList<>()));
            }
        }
        filterChain.doFilter(req,res);
    }
}
