package com.tax.calculation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tax.calculation.model.IncomeDetModel;

public interface IncomeRepo extends JpaRepository<IncomeDetModel, Long> {

	//public IncomeDetModel findById(long incomeId);
	public IncomeDetModel findById(long incomeId);
	//public List<IncomeDetModel> findByUserId(long userId);
}
