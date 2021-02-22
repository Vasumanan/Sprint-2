package com.cg.ngo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.ngo.beans.Admin;
@Repository
public interface AdminRepository extends JpaRepository<Admin,String>{
	@Query(value = "Select admin from Admin admin where userName=:userName and password=:password")
	public Admin adminLogin(String userName,String password);
	
}
