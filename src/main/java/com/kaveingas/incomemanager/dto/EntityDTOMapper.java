package com.kaveingas.incomemanager.dto;

import java.util.List;

import javax.validation.Valid;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import com.kaveingas.incomemanager.account.Account;
import com.kaveingas.incomemanager.address.Address;
import com.kaveingas.incomemanager.expense.Expense;
import com.kaveingas.incomemanager.income.Income;
import com.kaveingas.incomemanager.spending.Spending;
import com.kaveingas.incomemanager.user.User;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EntityDTOMapper {

	User userDtoToUser(UserDTO userDTO);

	UserDTO userToUserDTO(User entity);

	User signupRequestToUser(SignupRequestDTO dto);

	List<Income> mapIncomeCreateDTOsToIncomes(List<IncomeCreateDTO> incomeCreateDTOs);

	List<Expense> mapExpenseCreateDTOsToExpenses(List<ExpenseCreateDTO> expenseDTOs);

	@Mappings({ 
			@Mapping(target = "id", ignore = true), 
			@Mapping(target = "uuid", ignore = true),
			@Mapping(target = "account.address", source = "address")
	})
	User patchUser(@MappingTarget User user, UserProfileUpdateDTO userProfileUpdateDTO);

	NonPrimaryUserProfileUpdateDTO mapUserProfileUpdateDTOToNonPrimaryUserProfileUpdateDTO(
			UserProfileUpdateDTO userProfileUpdateDTO);

	@Mappings({ 
		@Mapping(target = "id", ignore = true), 
		@Mapping(target = "uuid", ignore = true),
		@Mapping(target = "account", ignore = true)
	})
	User patchNonPrimaryUser(@MappingTarget User user, NonPrimaryUserProfileUpdateDTO nonPrimaryUserProfileUpdateDTO);

	@Mappings({
		@Mapping(target = "address", source = "account.address")
	})
	UserProfileDTO mapUserToUserProfileDTO(User user);
	
	AddressDTO mapAddressToAddressDTO(Address address);
	
	Address patchAddress(@MappingTarget Address address, AddressDTO addressDTO);

	Spending mapSpendingCreateDTOToSpending(SpendingCreateDTO spendingDTO);
	
	List<Spending> mapSpendingCreateDTOsToSpendings(List<SpendingCreateDTO> spendingDTOs);
	
	SpendingDTO mapSpendingToSpendingDTO(Spending spending);
	
	List<SpendingDTO> mapSpendingsToSpendingDTOs(List<Spending> spendings);
}
