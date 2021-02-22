package com.cg.ngo.beans;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class DonationBox {
	
	private int registrationNumber;
	@Id
	private String ngoName;
	private String accountNumber;
	private double totalCollection;
	public String getNgoName() {
		return ngoName;
	}
	public void setNgoName(String ngoName) {
		this.ngoName = ngoName;
	}
	public int getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(int registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getTotalCollection() {
		return totalCollection;
	}
	public void setTotalCollection(double totalCollection) {
		this.totalCollection = totalCollection;
	}
}

