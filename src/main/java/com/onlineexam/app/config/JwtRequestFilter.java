package com.onlineexam.app.config;

import java.io.IOException;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(JwtRequestFilter.class);
	private static final String CLAIM_MOBILE_KEY = "mobile";
	private static final String CLAIM_COUNTRY_KEY = "country";

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers",
				"Content-Type, Accept, X-Requested-With, remember-me, Authorization, x-auth-token");
		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			final String requestTokenHeader = request.getHeader("Authorization");
			Map<Object, Object> claimMap = null;
			String jwtToken = null;
			/*
			 * JWT Token is in the form "Bearer token". Remove Bearer word and get only the
			 * Token
			 */
			if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
				jwtToken = requestTokenHeader.substring(7);
				try {
					claimMap = jwtTokenUtil.getUsernameAndCountryFromToken(jwtToken);
				} catch (IllegalArgumentException e) {
					LOGGER.error("Unable to get JWT Token");
				} catch (ExpiredJwtException e) {
					LOGGER.error("JWT Token has expired");
				}
			} else {
				logger.warn("JWT Token does not begin with Bearer String");
			}
			/*
			 * Once we get the token validate it.
			 */
			if (claimMap != null && claimMap.get(CLAIM_MOBILE_KEY) != null && claimMap.get(CLAIM_COUNTRY_KEY) != null
					&& SecurityContextHolder.getContext().getAuthentication() == null) {
				String mobileNumber = (String) claimMap.get(CLAIM_MOBILE_KEY);
				long country = Long.valueOf(claimMap.get(CLAIM_COUNTRY_KEY).toString());
////				PartnerUserMasterDao userMasterDao = iUserMasterDAO.findByuserMobileAndCountryId(mobileNumber, country);
////				PartnerMobileDAO partnerMobileDAO = null;
////				if (userMasterDao == null) {
////					partnerMobileDAO = iPartnerUserMobileDAO.findByuserMobileAndCountry(mobileNumber, country);
////				} else {
////					partnerMobileDAO = userMasterDao.getUserMobile();
////				}
//				if (jwtTokenUtil.validateToken(jwtToken, partnerMobileDAO)) {
//					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//							userMasterDao, null, null);
//					usernamePasswordAuthenticationToken
//							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//					/*
//					 * After setting the Authentication in the context, we specify that the current
//					 * user is authenticated. So it passes the Spring Security Configurations
//					 * successfully.
//					 */
//					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//				}
			}
			chain.doFilter(request, response);
		}
	}
}