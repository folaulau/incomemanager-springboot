package com.kaveingas.incomemanager.spending;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaveingas.incomemanager.dto.EntityDTOMapper;
import com.kaveingas.incomemanager.dto.SpendingCreateDTO;
import com.kaveingas.incomemanager.dto.SpendingDTO;
import com.kaveingas.incomemanager.utils.ApiSessionUtils;

@Service
public class SpendingServiceImp implements SpendingService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SpendingDAO spendingDAO;
	
	@Autowired
	private EntityDTOMapper entityDTOMapper;

	@Override
	public List<SpendingDTO> save(List<SpendingCreateDTO> spendings) {
		
		return entityDTOMapper.mapSpendingsToSpendingDTOs(spendingDAO.save(entityDTOMapper.mapSpendingCreateDTOsToSpendings(spendings)));
	}
}
