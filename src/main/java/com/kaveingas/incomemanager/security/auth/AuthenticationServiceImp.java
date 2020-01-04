package com.kaveingas.incomemanager.security.auth;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kaveingas.incomemanager.dto.SessionDTO;
import com.kaveingas.incomemanager.jwt.JwtPayload;
import com.kaveingas.incomemanager.jwt.JwtTokenUtils;
import com.kaveingas.incomemanager.user.User;
import com.kaveingas.incomemanager.utils.HttpUtils;
import com.kaveingas.incomemanager.utils.ObjectUtils;
import com.kaveingas.incomemanager.utils.RandomGeneratorUtils;

@Service
public class AuthenticationServiceImp implements AuthenticationService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	

	@Override
	public SessionDTO authenticate(User user, HttpServletRequest request) {
		//log.info("saved user: {}", ObjectUtils.toJson(user));
		JwtPayload jwtpayload = new JwtPayload(user, RandomGeneratorUtils.getJwtUuid());

		String clientUserAgent = HttpUtils.getRequestUserAgent(request);

		jwtpayload.setDeviceId(clientUserAgent);

		//log.info("jwtpayload: {}", ObjectUtils.toJson(jwtpayload));

		String jwtToken = JwtTokenUtils.generateToken(jwtpayload);

		SessionDTO ssnDto = new SessionDTO();
		ssnDto.setEmail(user.getEmail());
		ssnDto.setUserUuid(user.getUuid());
		ssnDto.setToken(jwtToken);
		ssnDto.setPrimary(user.isPrimary());
		ssnDto.setAccountUuid(user.getAccount().getUuid());
		ssnDto.setProfileSetupStatus(user.getAccount().getProfileSetupStatus());
		ssnDto.setFirstName(user.getFirstName());
		ssnDto.setLastName(user.getLastName());
		
		return ssnDto;
	}
	
	

}
