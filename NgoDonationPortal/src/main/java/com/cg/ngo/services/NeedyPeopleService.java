package com.cg.ngo.services;

import com.cg.ngo.beans.DonationDistribution;
import com.cg.ngo.beans.NeedyPeople;

import com.cg.ngo.exceptions.NoSuchNeedyPersonException;

public interface NeedyPeopleService {
	/**
	 * This registerNeedyPerson Method will register the NeedyPeople in system
	 * @param NeedyPeople to be registered
	 */
	public NeedyPeople save(NeedyPeople person);
	/**
	  * This login method will check the authenticity of the NeedyPeople
	  * @param userName of the NeedyPeople
	  * @param password of the NeedyPeople
	  * @return NeedyPeople if authenticated successfully of NoSuchneedyPersonException in case there is no NeedyPeople in DB.
	  */
	public String login(String userName, String password);
	/**
	 * This removeNeedyPeople Method will remove the NeedyPeople from system
	 * @param NeedyPeople to be removed 
	 */
	 
	/**
	 * This requestForHelp method will Send NeedyPeople Data as Request to DonationDistribution  
	 * @param requestForHelp to be sent to DonationDistribution
	 */
	
	 public DonationDistribution requestForHelp( String userName,String password);

}
