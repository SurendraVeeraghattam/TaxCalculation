package com.tax.calculation.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tax.calculation.model.UserDetModel;

@Repository
public interface UserRepo extends JpaRepository<UserDetModel, Long>  {
	
	public UserDetModel findById(long userId);
	public List<UserDetModel> findAll();
	public void deleteById(long userId);
	
	public List<UserDetModel> findByUserName(String userName);
//	public Page<UserDetModel> findByUserdetmodel(String userName, Pageable pageable);
	public Page<UserDetModel> findAllByUserName(String userName, Pageable pageable);
}
