package com.tt.shop.security;


import com.tt.shop.domain.User;
import com.tt.shop.domain.enumvalues.Role;
import com.tt.shop.exception.JwtValidateException;
import com.tt.shop.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    private static String SECRET_KEY = "Sui90mkl!@$";
    private static long timeToValidate = 3600000;

    private AppUserDetails appUserDetails;
    private UserService userService;

    public JwtTokenProvider(AppUserDetails appUserDetails, UserService userService) {
        this.appUserDetails = appUserDetails;
        this.userService = userService;
    }

    public String createToken(String username, List<Role> roles) throws JwtValidateException {

        User user = userService.findByUsername(username);

        Claims claims = Jwts.claims().setSubject(username);
        claims.put("auth", roles.stream()
                .map(s -> new SimpleGrantedAuthority(s.getAuthority()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));

        claims.put("userId", user.getId());
        claims.put("cartId", user.getCart().getId());

        Date dateNow = new Date();
        Date dateExpire = new Date(dateNow.getTime() + timeToValidate);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(dateNow)
                .setExpiration(dateExpire)
                .signWith(SignatureAlgorithm.HS512, getSecretKeyEncoded())
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = appUserDetails.loadUserByUsername(getUsernameFromToken(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(getSecretKeyEncoded()).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) throws JwtValidateException {
        try {
            Jwts.parser().setSigningKey(getSecretKeyEncoded()).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtValidateException("Invalid token");
        }
    }

    public String getSecretKeyEncoded() {
        return Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
    }
}

