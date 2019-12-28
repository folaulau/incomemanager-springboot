package com.kaveingas.incomemanager.role;

public interface RoleType {

	String ROLE_PREFIX = "ROLE_";

	String USER = "USER";
	String ROLE_USER = ROLE_PREFIX + "USER";

	String MANAGER = "MANAGER";
	String ROLE_MANAGER = ROLE_PREFIX + "MANAGER";

	String ADMIN = "ADMIN";
	String ROLE_ADMIN = ROLE_PREFIX + "ADMIN";

}
