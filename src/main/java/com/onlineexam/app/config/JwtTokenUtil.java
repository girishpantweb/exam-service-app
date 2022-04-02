package com.onlineexam.app.config;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.onlineexam.app.utils.CommonUtility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = -2550185165626007488L;
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	@Value("${security.jwt.client-secret}")
	private String secret;

	private static final String CLAIM_MOBILE_KEY = "mobile";
	private static final String CLAIM_COUNTRY_KEY = "country";

	/*
	 * Retrieve username from jwt token
	 */
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	/*
	 * Retrieve username from jwt token
	 */
	public Map<Object, Object> getUsernameAndCountryFromToken(String token) {
		Map<Object, Object> claimMap = new HashMap<Object, Object>();
		final Claims claims = getAllClaimsFromToken(token);
		claimMap.put(CLAIM_MOBILE_KEY, getClaimFromToken(claims, CLAIM_MOBILE_KEY));
		claimMap.put(CLAIM_COUNTRY_KEY, getClaimFromToken(claims, CLAIM_COUNTRY_KEY));
		return claimMap;
	}

	/*
	 * Retrieve expiration date from jwt token
	 */
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public boolean setExpirationDateForToken(String token, Date expirationDate) {
		final Claims claims = getAllClaimsFromToken(token);
		return claims.setExpiration(expirationDate) != null;
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	public Object getClaimFromToken(Claims claims, String key) {
		return claims.get(key);
	}

	// for retrieveing any information from token we will need the secret key
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

//check if the token has expired
	public Boolean isTokenExpired(String expiryDate) {
		LocalDateTime expiration = CommonUtility.formatToLocalDateTime(expiryDate);
		// final Date expiration = getExpirationDateFromToken(token);// from database
		Date expirationDate = Date.from(expiration.atZone(ZoneId.systemDefault()).toInstant());
		return expirationDate.before(new Date());
	}

	/*
	 * generate token for user
	 */
	public String generateToken(Object userDetails) {
//		Map<String, Object> claims = new HashMap<>();
//		claims.put(CLAIM_MOBILE_KEY, userDetails.getPartnerMobile());
//		claims.put(CLAIM_COUNTRY_KEY, userDetails.getCountry().getCountryId());
//		return doGenerateToken(claims,
//				userDetails.getPartnerMobile().concat(userDetails.getCountry().getCountryCode()));
		return null;
	}

	/*
	 * while creating the token - 1. Define claims of the token, like Issuer,
	 * Expiration, Subject, and the ID 2. Sign the JWT using the HS512 algorithm and
	 * secret key. 3. According to JWS Compact
	 * Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-
	 * 41#section-3.1) compaction of the JWT to a URL-safe string
	 */

	/// *JWT_TOKEN_VALIDITY * 1000*/
	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(CommonUtility.getFutureDate()).signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	/*
	 * validate token
	 */
	public Boolean validateToken(String token, Object partnerMobileDAO) {
//		Map<Object, Object> usermap = getUsernameAndCountryFromToken(token);
//		return ((usermap.get(CLAIM_MOBILE_KEY).toString().equals(partnerMobileDAO.getPartnerMobile()))
//				&& (Long.valueOf(usermap.get(CLAIM_COUNTRY_KEY).toString()) == (partnerMobileDAO.getCountry()
//						.getCountryId())) /* && !isTokenExpired(token) */);
		return false;
	}
}