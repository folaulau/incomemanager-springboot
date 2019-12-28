package com.kaveingas.incomemanager.user;

interface UserDAO {

	User save(User user);

	User getByEmail(String email);

	User getById(Long id);

	User getByUuid(String uuid);

	boolean doesEmailExist(String email);

}
