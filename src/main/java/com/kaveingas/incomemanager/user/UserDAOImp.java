package com.kaveingas.incomemanager.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kaveingas.incomemanager.dto.AccountStatsDTO;
import com.kaveingas.incomemanager.utils.ConversionUtils;

@Repository
public class UserDAOImp implements UserDAO {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private UserRepository userRepository;

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return userRepository.saveAndFlush(user);
	}

	@Override
	public User getByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email).orElse(null);
	}

	@Override
	public User getById(Long id) {
		// TODO Auto-generated method stub
		return userRepository.getById(id);
	}

	@Override
	public User getByUuid(String uuid) {
		// TODO Auto-generated method stub
		return userRepository.findByUuid(uuid).orElse(null);
	}

	@Override
	public boolean doesEmailExist(String email) {

		return userRepository.existsUserByEmail(email);
	}

	@Override
	public AccountStatsDTO getStats(String accountUuid) {
		log.debug("getStats(..)");
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT ");
		queryBuilder.append("acct.id, ");
		queryBuilder.append("acct.uuid, ");
		queryBuilder.append("SUM(distinct paycheck_net_amount) as TotalIncome, ");
		queryBuilder.append("SUM(distinct exp.amount)  as TotalEstimatedExpense, ");
		queryBuilder.append("SUM(distinct spend.amount) as TotalSpending ");
		queryBuilder.append("FROM income ");
		queryBuilder.append("JOIN user ON user.id = income.user_id ");
		queryBuilder.append("JOIN account as acct ON acct.id = user.account_id ");
		queryBuilder.append("LEFT JOIN expense as exp ON user.id = exp.user_id ");
		queryBuilder.append("LEFT JOIN spending_item as spend ON user.id = spend.user_id ");
		queryBuilder.append("GROUP BY acct.id ");
		queryBuilder.append("HAVING acct.uuid = ? ");
		
		AccountStatsDTO accountStatsDTO = jdbcTemplate.queryForObject(queryBuilder.toString(),
				new Object[] {accountUuid},
				new RowMapper<AccountStatsDTO>() {

					@Override
					public AccountStatsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
						log.debug("mapRow");
						AccountStatsDTO accountStatsDTO = new AccountStatsDTO();
						accountStatsDTO.setTotalIncome(rs.getDouble("TotalIncome"));
						accountStatsDTO.setTotalEstimatedExpense(rs.getDouble("TotalEstimatedExpense"));
						accountStatsDTO.setTotalSpending(rs.getDouble("TotalSpending"));

						// generate calculated values
						accountStatsDTO.setTotalSaving(accountStatsDTO.getTotalIncome()
								- accountStatsDTO.getTotalEstimatedExpense() - accountStatsDTO.getTotalSpending());
						
						double life = 
								accountStatsDTO.getTotalEstimatedExpense() - accountStatsDTO.getTotalSpending();
						
						accountStatsDTO.setLife(ConversionUtils.getTwoDecimalPlacesAsString(life));

						double lifePercentage = 0;

						if (accountStatsDTO.getTotalSpending() > 0 && accountStatsDTO.getTotalEstimatedExpense() > 0) {
							lifePercentage = 100
									* (accountStatsDTO.getTotalSpending() / accountStatsDTO.getTotalEstimatedExpense());
						}

						String status = "";

						if (lifePercentage >= 100) {
							status = "STOP - " + ConversionUtils.getTwoDecimalPlaces(lifePercentage) + "%";
						} else if (lifePercentage >= 80 && lifePercentage < 100) {
							status = "RUNNING LOW - " + ConversionUtils.getTwoDecimalPlaces(lifePercentage) + "%";
						} else {
							status = "STILL GOOD - " + ConversionUtils.getTwoDecimalPlaces(lifePercentage) + "%";
						}

						accountStatsDTO.setLifePercentage(lifePercentage);
						accountStatsDTO.setStatus(status);

						return accountStatsDTO;
					}
				});

		return accountStatsDTO;
	}

}
