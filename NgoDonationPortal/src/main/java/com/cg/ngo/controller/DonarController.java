package com.cg.ngo.controller;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.ngo.exceptions.NoSuchDonorException;
import com.cg.ngo.beans.Donation;
import com.cg.ngo.beans.Donor;
import com.cg.ngo.services.DonarService;
import com.cg.ngo.services.MapValidationErrorService;

@RestController
@RequestMapping("/ngo")
public class DonarController {

	@Autowired
	DonarService service;
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	@PostMapping("/register")
	public ResponseEntity<?> createDonor(@RequestBody@Valid  Donor donor,BindingResult result)  {
		ResponseEntity<?> errorMap= mapValidationErrorService.mapValidationError(result);
		if(errorMap!=null) 
			return errorMap;
		 donor = service.registerDonor(donor);
		return new ResponseEntity<Donor>(donor ,HttpStatus.CREATED);
	}
	@GetMapping("/loginDonor/{username}/{password}")
	public ResponseEntity<?> validateDonor(@PathVariable("username") String username, @PathVariable("password") String password) throws NoSuchDonorException {
		Donor donor = service.login(username, password);
		return new ResponseEntity<Donor>(donor ,HttpStatus.OK);	
	}
	@PostMapping("/add/donation/{donationBoxName}/{donarId}")
	public  ResponseEntity<?> addDonation(@RequestBody Donation donation,@PathVariable("donationBoxName") String donationBoxName,@PathVariable("donarId")int donarId) {
		donation = service.donateToNGO(donation,donationBoxName,donarId);
		return new ResponseEntity<Donation>(donation ,HttpStatus.OK);	
	}
}
