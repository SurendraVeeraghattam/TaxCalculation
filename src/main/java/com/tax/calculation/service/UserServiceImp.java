package com.tax.calculation.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.tax.calculation.controller.ApiException;
import com.tax.calculation.model.IncomeDetModel;
import com.tax.calculation.model.UserDetModel;
import com.tax.calculation.repository.IncomeRepo;
import com.tax.calculation.repository.UserRepo;

@Service
public class UserServiceImp implements IUserService {
	
	@Autowired
	private UserRepo repo;
	@Autowired
	private IncomeRepo Irepo;
	

	@Override
	public UserDetModel saveUserDetModel(UserDetModel userDetModel) {
		// TODO Auto-generated method stub
		System.out.println(userDetModel);
		List<UserDetModel> userDetModelEntity=repo.findAll();
		List<UserDetModel> userDetModel0 = new ArrayList<UserDetModel>();
		userDetModelEntity.forEach(userDetails ->{
			IncomeDetModel incomeDetModel1 = new IncomeDetModel();		
			UserDetModel userDetModel1=new UserDetModel();
			userDetModel1.setUserId(userDetails.getUserId());
			userDetModel1.setUserName(userDetails.getUserName());
			userDetModel1.setFinacialYear(userDetails.getFinacialYear());
			userDetModel1.setAge(userDetails.getAge());
			userDetModel1.setEarn(userDetails.getEarn());
			
			System.out.println("value of uderdetails"+userDetails.userId);
			incomeDetModel1 = Irepo.findById(userDetails.userId);
				System.out.println(incomeDetModel1.toString());
				IncomeDetModel incomeDetModel2=new IncomeDetModel();
				incomeDetModel2.setIncomeId(incomeDetModel1.getIncomeId());
				incomeDetModel2.setIncomeValue(incomeDetModel1.getIncomeValue());
				incomeDetModel2.setInterestValue(incomeDetModel1.getInterestValue());
				incomeDetModel2.setOtherSourceValue(incomeDetModel1.getOtherSourceValue());
				
				System.out.println("tax valuess"+incomeDetModel1.getTax());
				
				try {
					 incomeDetModel2.setTax((incomeDetModel1.getTax()));
					 
					 long incomeTaxValue = incomeDetModel2.getTax();
					 List<Long> list = Arrays.asList(incomeTaxValue);
					 
					 System.out.println("list values"+list);
					 String incomeTaxValues = Long.toString(incomeTaxValue); 
					 System.out.println("stream result"+list.stream().filter(s -> s < 0).collect(Collectors.toList()));
					//System.out.println("value of i"+i);
					 System.out.println("value of tax"+incomeTaxValue);
					 if((list.stream().filter(s -> s < 0).collect(Collectors.toList())) == null) {
					 throw new ApiException(incomeTaxValues);
					} 
				}
				catch (ApiException e1) {
					 System.out.println("Something went wrong.");
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					incomeDetModel2.setNetSalary(incomeDetModel1.getIncomeValue()-TotalTax(incomeDetModel1));
				} catch (ApiException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//incomeDetModel.add(incomeDetModel1);
			
			userDetModel1.setIncomeDetModel(incomeDetModel1);
			
			userDetModel0.add(userDetModel1);
		}
	  );
		
		return repo.save(userDetModel);
	}
	

	@Override
	public List<UserDetModel> getAllUserDetModels() {
		// TODO Auto-generated method stub
		List<UserDetModel> userDetModelEntity=repo.findAll();
		System.out.println("@@@@@"+userDetModelEntity.toString());
		List<UserDetModel> userDetModel = new ArrayList<UserDetModel>();
		userDetModelEntity.forEach(userDetails ->{
			IncomeDetModel incomeDetModel = new IncomeDetModel();
			UserDetModel userDetModel1=new UserDetModel();
			userDetModel1.setUserId(userDetails.getUserId());
			userDetModel1.setUserName(userDetails.getUserName());
			userDetModel1.setFinacialYear(userDetails.getFinacialYear());
			userDetModel1.setAge(userDetails.getAge());
			userDetModel1.setEarn(userDetails.getEarn());
			incomeDetModel = Irepo.findById(userDetails.userId);
				System.out.println("&&&&&&&&"+incomeDetModel.toString());
			userDetModel1.setIncomeDetModel(incomeDetModel);
			
			userDetModel.add(userDetModel1);
		}
	  );
		System.out.println("****"+userDetModel.toString());
		return userDetModel;
	}


	@Override
	public IncomeDetModel saveIncomeDetModel(IncomeDetModel incomeDetModel) throws ApiException {
		// TODO Auto-generated method stub
		System.out.println(incomeDetModel.toString());
		IncomeDetModel incomeDetModel1 = new IncomeDetModel();
		//String s = "abc";
		incomeDetModel1.setIncomeId(incomeDetModel.getIncomeId());
//		incomeDetModel1.setUser(incomeDetModel.getUser());
		incomeDetModel1.setIncomeValue(incomeDetModel.getIncomeValue());
		incomeDetModel1.setInterestValue(incomeDetModel.getInterestValue());
		incomeDetModel1.setOtherSourceValue(incomeDetModel.getOtherSourceValue());
		incomeDetModel1.setTax(TotalTax(incomeDetModel));
		incomeDetModel1.setNetSalary(incomeDetModel.getIncomeValue()-TotalTax(incomeDetModel));
		System.out.println( "fdgsrhjhjjj&&&&"+Irepo.save(incomeDetModel1));
		return incomeDetModel1;
	}

//	@Override
//	public List<IncomeDetModel> getAllIncomeDetModels() {
//		// TODO Auto-generated method stub
//		return Irepo.findAll();
//	}
	
	public long TotalTax(IncomeDetModel incomeDetModel) throws ApiException {
//		IncomeDetModel cal = new IncomeDetModel();
		long tax;
		if(incomeDetModel.IncomeValue > 1500000) {
			tax = (((incomeDetModel.IncomeValue+incomeDetModel.InterestValue+incomeDetModel.OtherSourceValue)*30/100)+50000);
		}
		else if(incomeDetModel.IncomeValue > 1200000) {
			tax = (((incomeDetModel.IncomeValue+incomeDetModel.InterestValue+incomeDetModel.OtherSourceValue)*15/100)+50000); 
		}
		else if(incomeDetModel.IncomeValue > 900000) {
			tax = (((incomeDetModel.IncomeValue+incomeDetModel.InterestValue+incomeDetModel.OtherSourceValue)*10/100)+50000);
		}
		else if(incomeDetModel.IncomeValue > 700000) {
			tax = (((incomeDetModel.IncomeValue+incomeDetModel.InterestValue+incomeDetModel.OtherSourceValue)*5/100)+50000);
		}
		else {
			tax = 0;
		}
		if(incomeDetModel.Tax > incomeDetModel.NetSalary) {
			throw new ApiException("Exception Encountered");
		}
		System.out.println(tax);
		return tax;
	}


	@Override
	public UserDetModel getUserDetModelsById(long userId) {
		// TODO Auto-generated method stub
		UserDetModel userDetModel = repo.findById(userId);
		System.out.println(userDetModel.toString());
		IncomeDetModel incomeDetModel = new IncomeDetModel();
		incomeDetModel = Irepo.findById(userDetModel.userId);
		System.out.println("&&&&&&&&"+incomeDetModel.toString());
		userDetModel.setIncomeDetModel(incomeDetModel);
		System.out.println("ffffff"+userDetModel+"eee");
		return userDetModel;
	}


	@Override
	public void deleteById(long userId) {
		// TODO Auto-generated method stub
		repo.deleteById(userId);
		Irepo.deleteById(userId);
		System.out.println("Delete successfully");
	}


	@Override
	public UserDetModel updateUserDetModelById(long userId, UserDetModel userDetModel) {
		// TODO Auto-generated method stub
		 UserDetModel userDetModel1 = repo.findById(userId);
			if(userDetModel1!=null) {
				userDetModel1.setUserName(userDetModel.getUserName());
				userDetModel1.setFinacialYear(userDetModel.getFinacialYear());
				userDetModel1.setAge(userDetModel.getAge());
				userDetModel1.setEarn(userDetModel.getEarn());
				IncomeDetModel incomeDetModel = Irepo.findById(userId);
				userDetModel1.setIncomeDetModel(incomeDetModel);
				incomeDetModel.setIncomeValue(incomeDetModel.getIncomeValue());
				incomeDetModel.setInterestValue(incomeDetModel.getInterestValue());
				incomeDetModel.setOtherSourceValue(incomeDetModel.getOtherSourceValue());
			}
			repo.save(userDetModel1);
		return userDetModel1;
	}


	@Override
	public List<UserDetModel> getAllUserDetModelsById(long userId) {
		// TODO Auto-generated method stub
		UserDetModel userDetModel = repo.findById(userId);
		System.out.println(userDetModel.toString());
		IncomeDetModel incomeDetModel = new IncomeDetModel();
		incomeDetModel = Irepo.findById(userDetModel.userId);
		System.out.println("&&&&&&&&"+incomeDetModel.toString());
		userDetModel.setIncomeDetModel(incomeDetModel);
		List<UserDetModel> userDetModel1 = new ArrayList<UserDetModel>();
		userDetModel1.add(userDetModel);
		System.out.println("------"+userDetModel1+"=====");
		return userDetModel1;
	}


	@Override
	public List<UserDetModel> getByUserdetmodel(String userName) {
		List<UserDetModel> user  = repo.findByUserName(userName);
		System.out.println("ghsdvggsdfge736354265"+user);
		return user;
	}


	
	@Override
	public Page<UserDetModel> getAllUserDetModel(String userName, Pageable pageable) {
		Page<UserDetModel> userDetModel = repo.findAll(pageable);
		List<UserDetModel> records = userDetModel.getContent();
		
		List<UserDetModel> listdata = new ArrayList<UserDetModel>();
			for(UserDetModel userDetModel1 : records ) {
				IncomeDetModel incomeDetModel = userDetModel1.getIncomeDetModel();
				incomeDetModel = Irepo.findById(userDetModel1.userId);
				userDetModel1.setIncomeDetModel(incomeDetModel);
				System.out.println("----------"+ incomeDetModel );
				listdata.add(userDetModel1);
			}
		// TODO Auto-generated method stub
		
		if(userName == null || userName.isEmpty()) {
			return userDetModel;
		}
		else {
			Page<UserDetModel> userFilt = repo.findAllByUserName(userName, pageable);
			System.out.println("hsdfbuyadb---"+ userFilt);
			System.out.println("$$$$$$$$$$"+ userName +"@@@@@@@@@@"+ repo.findByUserName(userName).toString());
			return userFilt;
		}
	}

}
