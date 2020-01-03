package com.kaveingas.incomemanager.utils;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.kaveingas.incomemanager.jwt.JwtPayload;

public class ApiSessionUtils {

	static Logger log = LoggerFactory.getLogger(ApiSessionUtils.class);

	public static JwtPayload getApiSession() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			JwtPayload session = (JwtPayload) auth.getPrincipal();
			if (session != null) {
				return session;
			}
		}
		return null;
	}

	public static Long getApiSessionUserId() {

		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			if (auth != null) {
				JwtPayload session = (JwtPayload) auth.getPrincipal();

				if (session != null && session.getUid() != null) {
					return session.getUid();
				}

			}
		} catch (Exception e) {
			log.warn(e.getLocalizedMessage());
		}

		return new Long(0);
	}

	public static Long getApiSessionAccountId() {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				JwtPayload session = (JwtPayload) auth.getPrincipal();

				if (session != null && session.getAcctId() != null) {
					return session.getAcctId();
				}

			}
		} catch (Exception e) {
			log.warn(e.getLocalizedMessage());

		}

		return new Long(0);
	}

	public static Long getCreateBy() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			JwtPayload session = (JwtPayload) auth.getPrincipal();

			if (session != null && session.getId() != null) {
				return session.getId();
			}

		}
		return new Long(1);
	}

	/**
	 * set jwtPayload in UsernamePasswordAuthenticationToken principal
	 * 
	 * @param jwtPayload
	 */
	public static void setRequestSecurityAuthentication(JwtPayload jwtPayload) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		if (jwtPayload.getAuthorities() != null || jwtPayload.getAuthorities().isEmpty() == false) {
			for (String role : jwtPayload.getAuthorities()) {
				authorities.add(new SimpleGrantedAuthority(role.toUpperCase()));
			}
		}

		SecurityContextHolder.getContext().setAuthentication(
				new UsernamePasswordAuthenticationToken(jwtPayload, jwtPayload.getUuid(), authorities));
	}

}
