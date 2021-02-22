package com.cg.ngo.controller;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ngo.beans.DonationDistribution;
import com.cg.ngo.beans.NeedyPeople;
import com.cg.ngo.services.MapValidationErrorService;
import com.cg.ngo.services.NeedyPeopleServiceImpl;

@RestController
@RequestMapping("/ngo/needypeople")
public class NeedyPeopleController {
	
	@Autowired
	 NeedyPeopleServiceImpl needyPeopleServiceImpl;
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@PostMapping("/saveNeedyPeople")
	public ResponseEntity<?> registerNeedyPeople(@Valid @RequestBody NeedyPeople needyPeople,BindingResult result)
	{
		ResponseEntity<?> errorMap= mapValidationErrorService.mapValidationError(result);
		if(errorMap!=null) 
			return errorMap;
		NeedyPeople needy= needyPeopleServiceImpl.save(needyPeople);
		return new ResponseEntity<NeedyPeople>(needy ,HttpStatus.CREATED);
	}	
	
	@GetMapping("/{userName}")
	public ResponseEntity<?> getNeedyPeopleByUserName(@PathVariable String userName){
		NeedyPeople needyPeople = needyPeopleServiceImpl.findNeedyPeopleByUserName(userName);
		return new ResponseEntity<NeedyPeople>(needyPeople, HttpStatus.OK);
	}
	
	@GetMapping("/login/{userName}/{password}")
	public ResponseEntity<String> validateNeedyPeople(@PathVariable("userName") String userName, @PathVariable("password") String password) 
	{
		return new ResponseEntity<String>(needyPeopleServiceImpl.login(userName, password),HttpStatus.FOUND);
	}
	
	
	@DeleteMapping("/{needyPersonId}")
	public ResponseEntity<?> deleteNeedyPeopleById(@PathVariable int needyPersonId) {
		needyPeopleServiceImpl.deleteByneedyPersonId(needyPersonId);
		return new ResponseEntity<String>("Project with Id "+needyPersonId+" Deleted SucessFully.", HttpStatus.OK);
	}
	
	@GetMapping("/requestForHelp/{userName}/{password}")
	public DonationDistribution requestForHelp(@PathVariable("userName") String userName, @PathVariable("password") String password) 
	{
		DonationDistribution needyPeople = needyPeopleServiceImpl.requestForHelp(userName, password);
		return needyPeople;
	}
	
}
