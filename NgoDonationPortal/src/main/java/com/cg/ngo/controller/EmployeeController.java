package com.cg.ngo.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.cg.ngo.beans.DonationDistribution;
import com.cg.ngo.beans.NeedyPeople;
import com.cg.ngo.services.EmployeeServiceImpl;


@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeServiceImpl employeeServiceImpl;
	
	@GetMapping("/validateEmployee/{username}/{password}")
	public ResponseEntity<String> validateEmployee(@PathVariable("username") String username, @PathVariable("password") String password) {
		String result="";
		result=employeeServiceImpl.login( username, password);
		return new ResponseEntity<String>(result, HttpStatus.OK);
    }
	
	@GetMapping("/getNeedyPerson/{needyPersonId}")
	public  ResponseEntity<?> getNeedyPersonById(@PathVariable int needyPersonId)
	{
		
		return new ResponseEntity<NeedyPeople>(employeeServiceImpl.getNeedyPeopleById(needyPersonId), HttpStatus.OK);
	}
		
	@GetMapping("/getAllNeedyPeople")
	public List<NeedyPeople> getAllNeedyPeople()
	{
		return employeeServiceImpl.getAllNeedyPeople();
	}
	
	@DeleteMapping("/deleteNeedyPerson/{id}")
 	public ResponseEntity<String> deleteNeedyPerson(@PathVariable("id") int id) {
 		int result = employeeServiceImpl.removeNeedyPerson(id);
 		return new ResponseEntity<String>("NeedyPerson with id "+result+ " deleted succesfully:", HttpStatus.OK);
 	}
	@GetMapping("/getNeedyPeople/{needyPersonName}")
	public ResponseEntity<List<NeedyPeople>> getNeedyPeopleByName(@PathVariable String needyPersonName) {
		List<NeedyPeople> needyPeopleList = employeeServiceImpl.getNeedyPeopleByName(needyPersonName);
		return new ResponseEntity<List<NeedyPeople>>(needyPeopleList, HttpStatus.OK);
	}
	
	@GetMapping("/helpNeedy/{needyPersonName}")
	public  DonationDistribution helpNeedyPerson(@PathVariable String needyPersonName){	
		return (employeeServiceImpl.helpNeedyPerson(needyPersonName));
	}
	
}