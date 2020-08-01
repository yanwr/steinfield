package com.my.steinfield.Steinfield.security;

import com.my.steinfield.Steinfield.models.enuns.Profiles;
import com.my.steinfield.Steinfield.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.Date;

@Component
public class JWTBuilder {

    @Value("${jwt.secret}")
    private String keySecret;
    @Value("${jwt.expiration}")
    private Long timeExpiration;

    @Autowired
    private UserRepository userRepository;

    public String generateToken(UserSS user){
        return Jwts.builder().setSubject(user.getId().toString())
                .setExpiration(new Date(System.currentTimeMillis() + this.timeExpiration))
                .claim("admin", this.isAdmin(user.getAuthorities()))
                .claim("email", user.getUseremail())
                .claim("name", user.getUsername())
                .signWith(SignatureAlgorithm.HS512, keySecret.getBytes()).compact();
    }

    public boolean validToken(String token) {
        Claims claims = getClaims(token);
        if(claims != null){
            String userId = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());
            if(userId != null && expirationDate != null && now.before(expirationDate)){
                return true;
            }
        }
        return false;
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(keySecret.getBytes()).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public String getUserEmail(String token) {
        Claims claims = getClaims(token);
        if (claims != null) {
            return claims.get("email").toString();
        }
        return null;
    }

    private boolean isAdmin (Collection<? extends GrantedAuthority> profiles) {
        for(GrantedAuthority ga : profiles){
           if (ga.getAuthority().equals(Profiles.ADMIN.getDesc())) {
               return true;
           }
        }
        return false;
    }
}