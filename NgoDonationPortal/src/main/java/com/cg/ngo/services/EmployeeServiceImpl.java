package com.cg.ngo.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ngo.beans.DonationBox;
import com.cg.ngo.beans.DonationDistribution;
import com.cg.ngo.beans.DonationDistributionStatus;
import com.cg.ngo.beans.Employee;
import com.cg.ngo.beans.NeedyPeople;
import com.cg.ngo.exceptions.NoSuchEmployeeException;
import com.cg.ngo.exceptions.NoSuchNeedyPersonException;
import com.cg.ngo.repository.DonationBoxRepository;
import com.cg.ngo.repository.DonationDistributionRepository;
import com.cg.ngo.repository.EmployeeRepository;
import com.cg.ngo.repository.NeedyPeopleRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private DonationBoxRepository donationBoxRepository;

	@Autowired
	DonationDistributionRepository donationDistributionRepository;

	@Autowired
	private NeedyPeopleRepository needyPeopleRepository;
	@Override
	public String login(String userName, String password) throws NoSuchEmployeeException {
		Employee employee = employeeRepository.employeeLogin(userName, password);
		if(employee == null) {
			throw new NoSuchEmployeeException("No Employee Found");
		}
		else
		{
			String userName1 = employee.getUserName();
			String password2 = employee.getPassword();
			if (userName.equals(userName1) && password.equals(password2))
			{
				return "LOGIN SUCCESSFUL!!";
			}
		}
		return "Invalid Credentials";
	}

	@Override
	public int removeNeedyPerson(int needyPersonId) {
		NeedyPeople needyPeople=needyPeopleRepository.findByneedyPersonId(needyPersonId);
		if(needyPeople==null)
		{
		 throw new NoSuchNeedyPersonException("No Needy Person found with this id");
		}
		needyPeopleRepository.delete(needyPeople);
		return needyPersonId;	
			}

	@Override
	public NeedyPeople getNeedyPeopleById(int needyPersonId) {
		if(needyPeopleRepository.existsById(needyPersonId)) {
			return needyPeopleRepository.findByneedyPersonId(needyPersonId);
		}
		else
		throw new NoSuchNeedyPersonException("Needy Person Id :"+needyPersonId+" does not exist.");
	}

	@Override
	public List<NeedyPeople> getNeedyPeopleByName(String needyPersonName) {
		List<NeedyPeople> needyPeopleList=needyPeopleRepository.findNeedyPeopleByName(needyPersonName);
		if(needyPeopleList.isEmpty()) {
			throw new NoSuchNeedyPersonException("Needy Person not found with this name.");
		}
		
		return needyPeopleList;
		}

	@Override
	public List<NeedyPeople> getAllNeedyPeople() {
		List<NeedyPeople> needyPeopleList=(List<NeedyPeople>) needyPeopleRepository.findAll();
		return needyPeopleList;
	}
	@Override
	public DonationDistribution helpNeedyPerson(String needyPersonUserName) {
		DonationDistribution donationDistribution=donationDistributionRepository.helpNeedyPerson(needyPersonUserName);
		DonationBox donationBox;
		if(donationDistribution.getStatus().equals(DonationDistributionStatus.APPROVED)) 
		{
			donationDistribution.setAmountDistributed(1000);

			donationDistribution.setDateOfDistribution(LocalDate.now());
			donationDistributionRepository.save(donationDistribution);
			donationBox = donationBoxRepository.getOne("NGO Donation");
			donationBox.setTotalCollection(donationBox.getTotalCollection()-1000);
			donationBoxRepository.save(donationBox);

		}
		return donationDistribution;
	}
}

