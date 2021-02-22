package com.cg.ngo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ngo.beans.Employee;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


@Repository
public interface EmployeeRepository  extends JpaRepository<Employee,Long> {
	
	public Employee findByUserName(String userName);
	public Employee findById(long employeeId);
	public boolean existsByUserName(String userName);
	@Query(value = "Select employee from Employee employee where employeeName =:name")
	public List<Employee> findAllByName(String name);	
	@Query(value = "Select employee from Employee employee where userName=:userName and password=:password")
	public Employee employeeLogin(String userName,String password);
}

