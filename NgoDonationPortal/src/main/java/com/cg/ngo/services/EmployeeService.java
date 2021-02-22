package com.cg.ngo.services;

import java.util.List;
import com.cg.ngo.beans.DonationDistribution;
import com.cg.ngo.beans.NeedyPeople;
import com.cg.ngo.exceptions.NoSuchEmployeeException;
/**
 * This EmployeeService will perform the operations related to the NeedyPeople.
 * @author Harika
 *
 */
public interface EmployeeService {
	/**
	 * This login method will check the login credentials from the Database.
	 * @param employee it is the Object of Employee which is used to login.
	 * @return true if authenticated successfully. NoSuchEmployeeException in case there is no Employee in DB.
	 */
	public String login(String userName, String password)throws NoSuchEmployeeException;
	/**
	 * This removeNeedyPerson method is used to delete the Needy Person in the Database.
	 * @param needyPersonId it is the Id of needy person which is to be remove in Database.
	 * @return This method will return id  if authenticated successfully.NoSuchNeedyPersonException in case there is no Employee in DB.
	 */
	public int removeNeedyPerson(int id);
	/**
	 * This getNeedyPeopleById method is used to get the Needy People by Id in the Database.
	 * @param needyPersonId it is the Id of Needy People which is to be read in Database.
	 * @return This method will return NeedyPeople object if authenticated successfully.NoSuchNeedyPersonException in case there is no needy person in DB.
	 */
	public NeedyPeople getNeedyPeopleById(int needyPersonId);
	/**
	 * This getNeedyPeopleByName method is used to get the  list of Needy People by name in the Database.
	 * @param needyPersonName it is the needy person name of  which is to be read in Database.
	 * @return This method will return the list of NeedyPeople objects with the name.
	 */
	public List<NeedyPeople> getNeedyPeopleByName(String needyPersonName);
	/**
	 * This getAllNeedyPeople method is used to get the  list of all the Needy People in the Database.
	 * @return This method will return the list of all the NeedyPeople objects.
	 */
	public List<NeedyPeople> getAllNeedyPeople();
	/**
	 * This helpNeedyPerson method is used to help the Needy People who are requesting for help.
	 * @param needyPersonUserName it is the needy person user name of  which is to be read in Database.
	 * @return This method will return the DonationDistribution object with that  user name.
	 */
	public DonationDistribution helpNeedyPerson(String needyPersonUserName);
}
