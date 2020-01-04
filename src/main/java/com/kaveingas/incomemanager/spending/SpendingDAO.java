package com.kaveingas.incomemanager.spending;

import java.util.List;

interface SpendingDAO {

	Spending save(Spending spending);
	
	List<Spending> save(List<Spending> spendings);
}
