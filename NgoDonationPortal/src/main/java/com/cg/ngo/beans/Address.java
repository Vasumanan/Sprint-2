package com.cg.ngo.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Entity
public class Address {

	@Id
	@GeneratedValue
	private int addressId;
	@NotBlank(message = "City Required.")
	private String city;
	@NotBlank(message = "State Required.")
	private String state;
	@NotBlank(message = "Pincode Required.")
	@Size(min = 6, message = "Enter Valid PinCode")
	private String pin;
	@NotBlank(message = "LandMark Required.")
	private String landmark;
	public Address() {
		
	}
	public Address(@NotBlank(message = "City Required.") String city,
			@NotBlank(message = "State Required.") String state,
			@NotBlank(message = "Pincode Required.") @Size(min = 6, message = "Enter Valid PinCode") String pin,
			@NotBlank(message = "LandMark Required.") String landmark) {
		super();
		
		this.city = city;
		this.state = state;
		this.pin = pin;
		this.landmark = landmark;
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPin() {
		return pin;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((landmark == null) ? 0 : landmark.hashCode());
		result = prime * result + ((pin == null) ? 0 : pin.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (landmark == null) {
			if (other.landmark != null)
				return false;
		} else if (!landmark.equals(other.landmark))
			return false;
		if (pin == null) {
			if (other.pin != null)
				return false;
		} else if (!pin.equals(other.pin))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

}
