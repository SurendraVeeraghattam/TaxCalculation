package com.tax.calculation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "Incomevalue")
public class IncomeDetModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IncomeId")
	private long incomeId;

//	@OneToOne(mappedBy = "Incomevalue")
//	private UserDetModel user;
	
	@Column(name = "IncomeValue")
	public long IncomeValue;
	
	@Column(name = "InterestValue")
	public long InterestValue;
	
	@Column(name = "OtherSourceValue")
	public long OtherSourceValue;
	
	@Column(name = "Tax")
	public long Tax;
	
	@Column(name = "NetSalary")
	public long NetSalary;
	
//	@OneToOne
//	@JoinColumn(name = "user_Id")
//	private UserDetModel user;
	
//	public UserDetModel getUser() {
//		return user;
//	}
//	public void setUser(UserDetModel user) {
//		this.user = user;
//	}
	@Override
	public String toString() {
		return "IncomeDetModel [incomeId=" + incomeId + ", IncomeValue=" + IncomeValue + ", InterestValue="
				+ InterestValue + ", OtherSourceValue=" + OtherSourceValue + ", Tax=" + Tax + ", NetSalary=" + NetSalary
				 + "]";
	}
	public long getIncomeId() {
		return incomeId;
	}
	public void setIncomeId(long incomeId) {
		this.incomeId = incomeId;
	}
//	public UserDetModel getUser() {
//		return user;
//	}
//	public void setUser(UserDetModel user) {
//		this.user = user;
	//}
	public long getIncomeValue() {
		return IncomeValue;
	}
	public void setIncomeValue(long incomeValue) {
		IncomeValue = incomeValue;
	}
	public long getInterestValue() {
		return InterestValue;
	}
	public void setInterestValue(long interestValue) {
		InterestValue = interestValue;
	}
	public long getOtherSourceValue() {
		return OtherSourceValue;
	}
	public void setOtherSourceValue(long otherSourceValue) {
		OtherSourceValue = otherSourceValue;
	}
	public long getTax() {
		return Tax;
	}
	public void setTax(long tax) {
		Tax = tax;
	}
	public long getNetSalary() {
		return NetSalary;
	}
	public void setNetSalary(long netSalary) {
		NetSalary = netSalary;
	}
	public IncomeDetModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public IncomeDetModel(long incomeId, long incomeValue, long interestValue, long otherSourceValue, long tax,
			long netSalary) {
		super();
		this.incomeId = incomeId;
		IncomeValue = incomeValue;
		InterestValue = interestValue;
		OtherSourceValue = otherSourceValue;
		Tax = tax;
		NetSalary = netSalary;
	}
	

}
