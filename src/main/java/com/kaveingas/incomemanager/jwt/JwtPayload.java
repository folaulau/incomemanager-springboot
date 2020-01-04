package com.kaveingas.incomemanager.jwt;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.kaveingas.incomemanager.user.User;
import com.kaveingas.incomemanager.utils.ObjectUtils;

/**
 * https://tools.ietf.org/html/rfc7519 JWT Payload
 * 
 */
@JsonInclude(value = Include.NON_NULL)
public class JwtPayload implements Serializable {

	private static final long serialVersionUID = -1L;

	// issuer
	private String iss;
	// id of jwt
	private String jti;

	private String email;

	// user uuid
	private String userUuid;

	// user id
	private Long userId;

	private String acctUuid;

	private Long acctId;

	private String deviceId;

	private List<String> authorities;

	private Boolean primary;

	// issued at
	private Date iat;
	// expired at
	private Date exp;
	// not before
	private Date nbf;

	public JwtPayload() {
		this(null);
	}

	public JwtPayload(String jti) {
		this(null, jti, null, null, null, null, null, null, null, null, null, null, null);
	}

	public JwtPayload(User user, String jti) {
		this(null, jti, user.getEmail(), user.getId(), user.getUuid(), user.getAuthorities(), null,
				user.getAccount().getUuid(), user.getAccount().getId(), user.isPrimary(), null, null, null);
	}

	public JwtPayload(String iss, String jti, String email, Long userId, String userUuid, List<String> authorities,
			String deviceId, String acctUuid, Long acctId, Boolean primary, Date iat, Date exp, Date nbf) {
		super();
		this.iss = iss;
		this.jti = jti;
		this.email = email;
		this.userUuid = userUuid;
		this.authorities = authorities;
		this.iat = iat;
		this.exp = exp;
		this.nbf = nbf;
		this.acctUuid = acctUuid;
		this.acctId = acctId;
		this.userId = userId;
		this.primary = primary;
	}

	public String getIss() {
		return iss;
	}

	public void setIss(String iss) {
		this.iss = iss;
	}

	public String getJti() {
		return jti;
	}

	public void setJti(String jti) {
		this.jti = jti;
	}

	public Boolean getPrimary() {
		return primary;
	}

	public void setPrimary(Boolean primary) {
		this.primary = primary;
	}

	public Date getIat() {
		return iat;
	}

	public void setIat(Date iat) {
		this.iat = iat;
	}

	public Date getExp() {
		return exp;
	}

	public void setExp(Date exp) {
		this.exp = exp;
	}

	public Date getNbf() {
		return nbf;
	}

	public void setNbf(Date nbf) {
		this.nbf = nbf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserUuid() {
		return userUuid;
	}

	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getAcctUuid() {
		return acctUuid;
	}

	public void setAcctUuid(String acctUuid) {
		this.acctUuid = acctUuid;
	}

	public Long getAcctId() {
		return acctId;
	}

	public void setAcctId(Long acctId) {
		this.acctId = acctId;
	}

	public String toJson() {
		try {
			return ObjectUtils.getObjectMapper().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			System.out.println(
					"JwtPayload - JwtPayload - toJson - JsonProcessingException, msg: " + e.getLocalizedMessage());
			return "{}";
		}
	}

}
