package com.tt.shop.security;


import com.tt.shop.domain.enumvalues.Role;
import com.tt.shop.exception.JwtValidateException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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

    @Value("${secretKey}")
    private static String SECRET_KEY;
    private static long timeToValidate = 3600000;

    private AppUserDetails appUserDetails;

    public JwtTokenProvider(AppUserDetails appUserDetails) {
        this.appUserDetails = appUserDetails;
    }

    public String createToken(String username, List<Role> roles) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("auth", roles.stream()
                .map(s -> new SimpleGrantedAuthority(s.getAuthority()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));

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
        System.out.println("i`am here");
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

