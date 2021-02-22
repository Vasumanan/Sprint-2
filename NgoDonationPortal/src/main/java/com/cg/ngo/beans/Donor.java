package com.cg.ngo.beans;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="Donors")
public class Donor {
	@Id
	@GeneratedValue
	private int donorId;
	
	private String donorName;
	
	private String donorEmail;
	private String donorPhone;
	@Column(unique = true,updatable = false)
	private String donorUserName;
	private String donorPassword;
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
	public Donor(int donorId, String donorName, String donorEmail, String donorPhone) {
		super();
		this.donorId = donorId;
		this.donorName = donorName;
		this.donorEmail = donorEmail;
		this.donorPhone = donorPhone;
	}
	public Donor(int donorId, String donorName, String donorEmail, String donorPhone, String donorUserName,
			String donorPassword, Address address) {
		super();
		this.donorId = donorId;
		this.donorName = donorName;
		this.donorEmail = donorEmail;
		this.donorPhone = donorPhone;
		this.donorUserName = donorUserName;
		this.donorPassword = donorPassword;
		this.address = address;
	}
	public Donor(String donorUserName, String donorPassword) {
		this.donorUserName =donorUserName;
		this.donorPassword =donorPassword;
	}
	public Donor() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Donor other = (Donor) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (donorEmail == null) {
			if (other.donorEmail != null)
				return false;
		} else if (!donorEmail.equals(other.donorEmail))
			return false;
		if (donorId != other.donorId)
			return false;
		if (donorName == null) {
			if (other.donorName != null)
				return false;
		} else if (!donorName.equals(other.donorName))
			return false;
		if (donorPassword == null) {
			if (other.donorPassword != null)
				return false;
		} else if (!donorPassword.equals(other.donorPassword))
			return false;
		if (donorPhone == null) {
			if (other.donorPhone != null)
				return false;
		} else if (!donorPhone.equals(other.donorPhone))
			return false;
		if (donorUserName == null) {
			if (other.donorUserName != null)
				return false;
		} else if (!donorUserName.equals(other.donorUserName))
			return false;
		return true;
	}
	public int getDonorId() {
		return donorId;
	}
	public void setDonorId(int donorId) {
		this.donorId = donorId;
	}
	public String getDonorName() {
		return donorName;
	}
	public void setDonorName(String donorName) {
		this.donorName = donorName;
	}
	public String getDonorEmail() {
		return donorEmail;
	}
	public void setDonorEmail(String donorEmail) {
		this.donorEmail = donorEmail;
	}
	public String getDonorPhone() {
		return donorPhone;
	}
	public void setDonorPhone(String donorPhone) {
		this.donorPhone = donorPhone;
	}
	public String getDonorUsername() {
		return donorUserName;
	}
	public void setDonorUsername(String donorUsername) {
		this.donorUserName = donorUsername;
	}
	public String getDonorPassword() {
		return donorPassword;
	}
	public void setDonorPassword(String donorPassword) {
		this.donorPassword = donorPassword;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	

    @Override
    public int hashCode() {
        return Objects.hash(donorUserName,donorPassword);
    }
	@Override
	public String toString() {
		return "Donor [donorId=" + donorId + ", donorName=" + donorName + ", donorEmail=" + donorEmail + ", donorPhone="
				+ donorPhone + ", donorUserName=" + donorUserName + ", donorPassword=" + donorPassword + ", address="
				+ address + "]";
	}
}
