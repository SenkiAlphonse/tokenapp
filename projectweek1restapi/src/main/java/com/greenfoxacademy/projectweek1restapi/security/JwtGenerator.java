package com.greenfoxacademy.projectweek1restapi.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import com.greenfoxacademy.projectweek1restapi.model.JwtUser;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtGenerator {


  public String generate(JwtUser jwtUser) {
    Date now = new Date();
    long t = now.getTime();
    Date expirationTime = new Date(t + 60000l);

    Claims claims = Jwts.claims()
        .setSubject(jwtUser.getUserName());
    claims.put("userId", String.valueOf(jwtUser.getId()));
    claims.put("role", jwtUser.getRole());


    return Jwts.builder()
        .setClaims(claims)
        .signWith(SignatureAlgorithm.HS512, "LoremipsumdolorsitametconsecteturLoremipsumdolorsitametconsecteturLoremipsumdolorsitametconsecteturLoremipsumdolorsitametconsecteturLoremipsumdolorsitametconsecteturLoremipsumdolorsitametconsectetur")
        .setExpiration(expirationTime)
        .compact();
  }
}
