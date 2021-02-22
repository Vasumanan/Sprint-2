package com.cg.ngo.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ngo.exceptions.DuplicateDonorException;
import com.cg.ngo.exceptions.NoSuchDonorException;
import com.cg.ngo.beans.Donation;
import com.cg.ngo.beans.DonationBox;
import com.cg.ngo.beans.DonationItem;
import com.cg.ngo.beans.Donor;
import com.cg.ngo.repository.DonarRepository;
import com.cg.ngo.repository.DonationBoxRepository;
import com.cg.ngo.repository.DonationItemRepository;
import com.cg.ngo.repository.DonationRepository;


@Service
@Transactional
public class DonarServiceImplemnetation implements DonarService {

	
	@Autowired
	DonarRepository donorRepository;
	@Autowired
	DonationBoxRepository donationBoxRepository;
	@Autowired
	DonationItemRepository donationItemRepository;
	@Autowired
	DonationRepository donationRepository;
	
	@Override
	public Donor registerDonor(Donor donor)  {
		try {
			return donorRepository.save(donor);
		}
		catch (Exception e) {
			throw new DuplicateDonorException("Donor with same name exists already");
		}
	
		

	}
    @Override
	public Donor login(String username, String password) throws NoSuchDonorException {
		Donor donor = donorRepository.findByDonorUserNameAndDonorPassword(username, password);
		if(donor == null) {
			throw new NoSuchDonorException("No Matching Username And Password");
		}
		return donor;
	}

	@Override
	public Donation donateToNGO(Donation donation,String donationBoxName,int donorId) {
		DonationBox donationBox = donationBoxRepository.getOne(donationBoxName);
		donationBox.setTotalCollection(donation.getDonationAmount()+donationBox.getTotalCollection());
		donationBoxRepository.save(donationBox);
		donation.setDonor(donorRepository.findById(donorId).get());
//		donationItemRepository.save(donation.getItem());
		donation = donationRepository.save(donation);
		return donation;
	}
}





