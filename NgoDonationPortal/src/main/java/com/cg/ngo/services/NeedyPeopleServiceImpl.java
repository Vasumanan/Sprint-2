package com.cg.ngo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.ngo.beans.DonationDistribution;
import com.cg.ngo.beans.DonationDistributionStatus;
import com.cg.ngo.beans.NeedyPeople;
import com.cg.ngo.exceptions.DuplicateNeedyPersonException;
import com.cg.ngo.exceptions.NoSuchNeedyPersonException;
import com.cg.ngo.repository.DonationDistributionRepository;
import com.cg.ngo.repository.NeedyPeopleRepository;

@Service
public class NeedyPeopleServiceImpl implements NeedyPeopleService {

	@Autowired
	NeedyPeopleRepository needyPeopleRepository;
	@Autowired
	DonationDistributionRepository donationDistributionRepository;

	public NeedyPeople save(NeedyPeople person)
	{
		try {
			return needyPeopleRepository.save(person);
		}
		catch(Exception e)
		{
			throw new DuplicateNeedyPersonException("Needy People: "+person.getUserName().toUpperCase()+" is Already Exist");
		}
	}

	public NeedyPeople findNeedyPeopleByUserName(String userName) {
		NeedyPeople needyPeople= needyPeopleRepository.findByUserName(userName);
		if(needyPeople==null)
		{
			throw new NoSuchNeedyPersonException("NeedyPerson Does Not Exist.");
		}
		return needyPeople;
	}

	public String login(String userName, String password) {
		NeedyPeople needyPeople = needyPeopleRepository.findByUserNameAndPassword(userName, password);
		if(needyPeople == null) {
			throw new NoSuchNeedyPersonException("No Matching Username And Password");
		}
          return "login Successful";
	}


	public NeedyPeople deleteByneedyPersonId(int needyPersonId) {
		NeedyPeople needyPeople=needyPeopleRepository.findByneedyPersonId(needyPersonId);
		if(needyPeople==null)
		{
			throw new NoSuchNeedyPersonException("Cannot Delete, NeedyPeople Does Not Exist");
		}
		needyPeopleRepository.delete(needyPeople);
		return needyPeople;

	}

	public DonationDistribution requestForHelp( String userName,String password)
	{
		DonationDistribution donationDistribution=new DonationDistribution();
		NeedyPeople needyPeople=needyPeopleRepository.findByUserNameAndPassword( userName, password );
		donationDistribution.setNeedyPersonUserName(needyPeople.getUserName());
		donationDistribution.setDistributionId(needyPeople.getNeedyPersonId());
		donationDistribution.setStatus(DonationDistributionStatus.PENDING);
		donationDistribution.setNeedyPersonFamilyIncome(needyPeople.getFamilyIncome());
		donationDistributionRepository.save(donationDistribution);
		return donationDistribution;
	}

}