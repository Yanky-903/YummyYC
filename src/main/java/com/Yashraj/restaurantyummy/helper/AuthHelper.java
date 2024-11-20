package com.Yashraj.restaurantyummy.helper;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthHelper {
    private final JWTHelper jwtHelper;

    public AuthHelper(JWTHelper jwtHelper) {
        this.jwtHelper = jwtHelper;
    }

    public String extractAndCheckJWT(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer")) {
            String token = header.substring(7); // Remove "Bearer " prefix
            try {
                if (jwtHelper.validateToken(token)) {
                    return jwtHelper.extractUsername(token); // Return the email if valid
                }
            } catch (ExpiredJwtException e) {
                System.out.println("Token has expired: " + e.getMessage());
            } catch (SignatureException e) {
                System.out.println("Invalid token signature: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Token validation error: " + e.getMessage());
            }
        }
        return null; // Return null if token is invalid or not present
    }
}