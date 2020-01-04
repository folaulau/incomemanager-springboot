package com.kaveingas.incomemanager.user;

import com.kaveingas.incomemanager.dto.AccountStatsDTO;

interface UserDAO {

	User save(User user);

	User getByEmail(String email);

	User getById(Long id);

	User getByUuid(String uuid);

	boolean doesEmailExist(String email);
	
	AccountStatsDTO getStats(String accountUuid);

}
