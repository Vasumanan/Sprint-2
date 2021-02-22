package com.cg.ngo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.cg.ngo.beans.Donor;

public interface DonarRepository extends JpaRepository<Donor, Integer> {
	public Donor findByDonorUserNameAndDonorPassword(String username, String password);
	public Donor findByDonorUserName(String username);
}
