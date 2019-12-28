package com.kaveingas.incomemanager.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUuid(String uuid);
	
	User getById(Long id);
	
	User getByUuid(String uuid);
	
	Optional<User> findByEmail(String email);
	
	boolean existsUserByEmail(String email);
}
