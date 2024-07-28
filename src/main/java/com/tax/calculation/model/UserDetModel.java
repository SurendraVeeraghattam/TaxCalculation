package com.tax.calculation.model;

import java.util.List;

//import javax.persistence.CascadeType;
//import javax.persistence.FetchType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Userdetails")
public class UserDetModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UserId")
	public long userId;
	
//	@OneToOne
//	@JoinColumn(name = "UserId",referencedColumnName = "user_Id")
//	private IncomeDetModel Incomevalue;

	@Column(name = "UserName")
	private String userName;
	@Column(name = "FinacialYear")
	private String finacialYear;
	@Column(name = "Age")
	private String age;
	@Column(name = "Earn")
	private String earn;
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFinacialYear() {
		return finacialYear;
	}


	public void setFinacialYear(String finacialYear) {
		this.finacialYear = finacialYear;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getEarn() {
		return earn;
	}

	public void setEarn(String earn) {
		this.earn = earn;
	}

	public UserDetModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	//@Column(name="income")
	@OneToOne(cascade = jakarta.persistence.CascadeType.ALL,fetch = jakarta.persistence.FetchType.EAGER)
	@JoinColumn(name="income")
	private IncomeDetModel  incomeDetModel;
	
	public UserDetModel(long userId, String userName, String finacialYear, String age, String earn,
			IncomeDetModel incomeDetModel) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.finacialYear = finacialYear;
		this.age = age;
		this.earn = earn;
		this.incomeDetModel = incomeDetModel;
	}

	@Override
	public String toString() {
		return "UserDetModel [userId=" + userId + ", userName=" + userName + ", finacialYear=" + finacialYear + ", age="
				+ age + ", earn=" + earn + ", incomeDetModel=" + incomeDetModel + "]";
	}

	
	public IncomeDetModel getIncomeDetModel() {
		return incomeDetModel;
	}

	public void setIncomeDetModel(IncomeDetModel incomeDetModel) {
		this.incomeDetModel = incomeDetModel;
	}

	
	
}
