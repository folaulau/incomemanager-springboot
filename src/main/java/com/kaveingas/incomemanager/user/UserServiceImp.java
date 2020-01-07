package com.kaveingas.incomemanager.user;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kaveingas.incomemanager.dto.SignupRequestDTO;
import com.kaveingas.incomemanager.dto.UserProfileDTO;
import com.kaveingas.incomemanager.dto.UserProfileUpdateDTO;
import com.kaveingas.incomemanager.exception.ApiError;
import com.kaveingas.incomemanager.exception.ApiException;
import com.google.common.base.Optional;
import com.kaveingas.incomemanager.account.Account;
import com.kaveingas.incomemanager.account.FunnelType;
import com.kaveingas.incomemanager.dto.AccountStatsDTO;
import com.kaveingas.incomemanager.dto.EntityDTOMapper;
import com.kaveingas.incomemanager.dto.NonPrimaryUserProfileUpdateDTO;
import com.kaveingas.incomemanager.role.Role;
import com.kaveingas.incomemanager.role.RoleType;
import com.kaveingas.incomemanager.utils.ApiSessionUtils;
import com.kaveingas.incomemanager.utils.ObjectUtils;
import com.kaveingas.incomemanager.utils.PasswordUtils;

@Service
public class UserServiceImp implements UserService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private EntityDTOMapper entityDTOMapper;

	@Override
	public User getById(Long id) {
		// TODO Auto-generated method stub
		return userDAO.getById(id);
	}

	@Override
	public User getProfileById(Long id) {
		// TODO Auto-generated method stub
		return userDAO.getById(id);
	}

	@Override
	public User findByUuid(String uuid) {
		// TODO Auto-generated method stub
		Optional<User> optUser = Optional.fromNullable(userDAO.getByUuid(uuid));

		if (optUser.isPresent() == false) {
			throw new ApiException(
					new ApiError(HttpStatus.BAD_REQUEST, "User not found", "No user found for uuid=" + uuid));
		}

		return optUser.get();
	}

	@Override
	public User getByUuid(String uuid) {
		// TODO Auto-generated method stub
		return userDAO.getByUuid(uuid);
	}

	@Override
	public User getByEmail(String email) {
		// TODO Auto-generated method stub
		return userDAO.getByEmail(email);
	}

	@Override
	public User signUp(SignupRequestDTO signupRequest) {
		log.debug("signup(..)");
		UserUtils.validateSignup(userDAO, signupRequest);

		Account account = new Account();
		account.setProfileSetupStatus(FunnelType.SIGNUP.getName());
		
		User user = this.entityDTOMapper.signupRequestToUser(signupRequest);
		user.setAccount(account);
		user.setPrimary(true);
		user.setActive(true);

		Role role = new Role();
		role.setAuthority(RoleType.USER);
		role.addUser(user);
		user.addRole(role);

		user.setPassword(PasswordUtils.hashPassword(user.getPassword()));
		log.debug("user: {}", ObjectUtils.toJson(user));

		user = userDAO.save(user);

		return user;
	}

	@Override
	public UserProfileDTO updateProfile(UserProfileUpdateDTO userProfileUpdateDTO, String funnel) {

		User user = findByUuid(userProfileUpdateDTO.getUuid());

		if (ApiSessionUtils.isCurrentUserPrimary()) {
			user = entityDTOMapper.patchUser(user, userProfileUpdateDTO);

		} else {
			NonPrimaryUserProfileUpdateDTO nonPrimaryUserProfileUpdateDTO = entityDTOMapper
					.mapUserProfileUpdateDTOToNonPrimaryUserProfileUpdateDTO(userProfileUpdateDTO);

			user = entityDTOMapper.patchNonPrimaryUser(user, nonPrimaryUserProfileUpdateDTO);

		}
		
		FunnelType funnelType = FunnelType.getByName(funnel);
		
		if(funnel!=null && funnelType!=null && funnelType.equals(FunnelType.PROFILE)) {
			user.getAccount().setProfileSetupStatus(FunnelType.DONE.getName());
		}

		user = this.userDAO.save(user);

		return entityDTOMapper.mapUserToUserProfileDTO(user);
	}

	@Override
	public UserProfileDTO getProfile(String uuid) {
		log.debug("getProfile({})", uuid);
		User user = findByUuid(uuid);
		return entityDTOMapper.mapUserToUserProfileDTO(user);
	}

	@Override
	public AccountStatsDTO getStats(String accountUuid) {
		// TODO Auto-generated method stub
		return userDAO.getStats(accountUuid);
	}

}
