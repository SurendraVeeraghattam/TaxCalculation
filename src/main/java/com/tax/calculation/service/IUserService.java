package com.tax.calculation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tax.calculation.controller.ApiException;
import com.tax.calculation.model.IncomeDetModel;
import com.tax.calculation.model.UserDetModel;
import com.tax.calculation.repository.UserRepo;

@Service
public interface IUserService {
	
	@Autowired
	public UserDetModel saveUserDetModel(UserDetModel userDetModel);
	public IncomeDetModel saveIncomeDetModel(IncomeDetModel incomeDetModel) throws ApiException;
	public List<UserDetModel> getAllUserDetModels();
	public UserDetModel getUserDetModelsById(long userId);
	public List<UserDetModel> getAllUserDetModelsById(long userId);
	public UserDetModel updateUserDetModelById(long userId, UserDetModel userDetModel);
	public void deleteById(long userId);
	public long TotalTax(IncomeDetModel incomeDetModel) throws ApiException;
	public List<UserDetModel> getByUserdetmodel(String userName);
	public Page<UserDetModel> getAllUserDetModel(String userName, Pageable pageable);
}
