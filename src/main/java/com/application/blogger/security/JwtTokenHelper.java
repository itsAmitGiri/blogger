package com.application.blogger.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@Component
public class JwtTokenHelper {
	
	public static final long JWT_TOKEN_VALIDITY = 5*60*60;
	
	private String secret = "jwtTokenKey";
	
	public String getUserNameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}
	
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimResolver.apply(claims);
	}
	
	public Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(expiration);
	}
	
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}
	
	private String doGenerateToken(Map<String, Object> claims, String subject) {
		
		
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+ JWT_TOKEN_VALIDITY  *100))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
				
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String userName = getUserNameFromToken(token);
		return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

}





















