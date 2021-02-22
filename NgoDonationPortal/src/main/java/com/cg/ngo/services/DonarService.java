package com.cg.ngo.services;



import com.cg.ngo.exceptions.DuplicateDonorException;
import com.cg.ngo.exceptions.NoSuchDonorException;
import com.cg.ngo.beans.Donation;
import com.cg.ngo.beans.Donor;
/**
 * 
 * @author Sapana
 *
 */
public interface DonarService {

	public Donor registerDonor(Donor donor) throws DuplicateDonorException;
	public Donor login(String username, String password) throws NoSuchDonorException;
	public Donation donateToNGO(Donation donation,String donationBoxName,int donorId);
}