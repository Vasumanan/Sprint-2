package com.cg.ngo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cg.ngo.beans.Employee;
import com.cg.ngo.services.AdminServiceImpl;

@RestController
public class AdminController {

	@Autowired
	private AdminServiceImpl adminServiceImpl;

	@GetMapping("/login/{userName}/{password}")
	public ResponseEntity<?> adminLogin(@PathVariable String userName ,@PathVariable String password ){
		return new ResponseEntity<String>(adminServiceImpl.login(userName, password),HttpStatus.FOUND);
	}
	
	@PostMapping("/saveEmployee")
	public ResponseEntity<?>  saveEmployee(@RequestBody Employee employee) {
		return new ResponseEntity<Employee>(adminServiceImpl.createEmployee(employee), HttpStatus.CREATED);
	}
	
	@GetMapping("/findEmployee/{employeeId}")
	public ResponseEntity<?> findEmployeeById(@PathVariable long employeeId){
		return new ResponseEntity<Employee>(adminServiceImpl.getEmployeeDetailsById(employeeId),HttpStatus.FOUND);
	}
	
	@GetMapping("/findAllEmployee")
	public ResponseEntity<?> findAllEmployee(){
		return new ResponseEntity<List>(adminServiceImpl.getAllEmployeeDetails(),HttpStatus.FOUND);
	}
    
	@DeleteMapping("/deleteEmployee/{userName}")
	public ResponseEntity<?>  deleteEmployee(@PathVariable String userName) {
		return new ResponseEntity<String>(adminServiceImpl.deleteEmployeeByUserName(userName),HttpStatus.OK);
	}
    
	@GetMapping("/findAllEmployeesByName/{employeeName}")
	public ResponseEntity<?>  findAllEmployeesByName(@PathVariable String employeeName){
		return new ResponseEntity<List>(adminServiceImpl.getEmployeeDetailsByName(employeeName),HttpStatus.FOUND);
	}

    @PutMapping("/UpdateEmployee")
	public  ResponseEntity<?>  updateEmployee(@RequestBody Employee employee){
		return new ResponseEntity<Employee>(adminServiceImpl.updateEmployee(employee),HttpStatus.OK);
	}
    
    @GetMapping("/approveOrRejectDonation/{donationBoxName}/{donationDistributionId}")
	public ResponseEntity<?> approveOrRejectDonation(@PathVariable String donationBoxName,@PathVariable long donationDistributionId){	
		return new ResponseEntity<String>(adminServiceImpl.approveOrRejectDonation(donationDistributionId, donationBoxName),HttpStatus.FOUND);
	}
}
