package com.kaveingas.incomemanager.spending;

import java.util.List;

import com.kaveingas.incomemanager.dto.SpendingCreateDTO;
import com.kaveingas.incomemanager.dto.SpendingDTO;

public interface SpendingService {

	List<SpendingDTO> save(List<SpendingCreateDTO> spendings);
}
