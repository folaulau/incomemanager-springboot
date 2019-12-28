package com.kaveingas.incomemanager.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import com.kaveingas.incomemanager.account.Account;
import com.kaveingas.incomemanager.user.User;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EntityDTOMapper {

	User userDtoToUser(UserDTO userDTO);

	UserDTO userToUserDTO(User entity);

	User signupRequestToUser(SignupRequestDTO dto);

}
