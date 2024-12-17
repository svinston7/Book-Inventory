package com.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {


	   private String secretKey="";
	    
	   public JwtService()  {
		   try {
			KeyGenerator keyGen= KeyGenerator.getInstance("HmacSHA256");
			SecretKey sk=keyGen.generateKey();
			secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
		/*public String generateToken(User user) {
			// TODO Auto-generated method stub
			Map<String,Object> claims=new HashMap<()>
			return Jwts
					.builder()
					.claims()
					.add(claims)
					 .subject(user.getUsername())
					 .issuer("DCB")
					 .issuedAt(new Date(System.currentTimeMillis()))
					 .expiration(new Date(System.currentTimeMillis() + 10*60*1000))
					 .signWith(generateKey())
					 .compact();
		}*/
		
		/*private SecretKey generateKey() {
			// TODO Auto-generated method stub
			byte[]decode=Decoders.BASE64.decode(getSecretKey());
			return Keys.hmacShaKeyFor(decode);
		}*/

		public String generateToken(String username) {
			
			Map<String,Object>claims= new HashMap<>();
			return Jwts
					.builder()
					.claims()
					.add(claims)
					.subject(username)
					.issuedAt(new Date(System.currentTimeMillis()))
					.expiration(new Date(System.currentTimeMillis()+60*60*1000))
					.and()
					.signWith(getKey())
					.compact();
					
			
		}

	private SecretKey getKey() {
		byte[] keyBytes=Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String extractUserName(String token) {
		// TODO Auto-generated method stub
		return extractClaim(token,Claims::getSubject);
	}

	private <T> T extractClaim(String token, Function<Claims,T> claimResolver) {
		// TODO Auto-generated method stub
		final Claims claims=extractAllClaims(token);
		return claimResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		// TODO Auto-generated method stub
		return Jwts.parser()
				.verifyWith(getKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
	public boolean validateToken(String token, UserDetails userDetails) {
		// TODO Auto-generated method stub
		return true;
	}
	

}
