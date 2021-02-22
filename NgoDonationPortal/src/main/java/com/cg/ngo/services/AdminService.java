package com.cg.ngo.services;

import java.util.List;
import com.cg.ngo.beans.Employee;
/**
 * This AdminService will perform all the Admin related services
 * @author Vasumanan J
 *
 */
public interface AdminService {
	
	/**
	 * This login method will check the login credentials in the Database.
	 * @param admin it is the Object of Admin which is to login.
	 * @return true if authenticated successfully. NoSuchAdminException in case there is no Admin in DB.
	 */
	public String login(String userName,String password);

	/**
	 * This addEmployee method will Save the Employee in the Database.
	 * @param employee it is the Object of Employee which is to be Added in Database.
	 * @return true if authenticated successfully.DuplicateEmployeeException in case when the employee already exist in DB.
	 */
	public Employee createEmployee(Employee employee);
	
	/**
	 * This modifyEmployee method is used to Update the Employee Which is already present in the Database.
	 * @param employee it is the Object of Employee which is to be updated in Database.
	 * @return This method will return Updated Employee object. NoSuchEmployeeException in case there is no Employee in DB.
	 */
	public Employee updateEmployee(Employee employee);
	
	/**
	 * This removeEmployee method is used to delete the Employee in the Database.
	 * @param employeeId it is the Id of Employee which is to be remove in Database.
	 * @return This method will return true  if authenticated successfully.NoSuchEmployeeException in case there is no Employee in DB.
	 */
	public String deleteEmployeeByUserName(String userName);

	/**
	 * This getEmployeeDetailsById method is used to get the Employee by Id in the Database.
	 * @param employeeId it is the Id of Employee which is to be read in Database.
	 * @return This method will return Employee object if authenticated successfully.NoSuchEmployeeException in case there is no Employee in DB..
	 */
	public Employee getEmployeeDetailsById(long employeeId);
	
	/**
	 * This getEmployeeDetailsByName method is used to get the Employee by Name in the Database.
	 * @param employeeName it is the Name of Employee which is to be read in Database.
	 * @return This method will return List of Employees if authenticated successfully.NoSuchEmployeeException in case there is no Employee in DB.
	 */
	public List<Employee> getEmployeeDetailsByName(String name) ;
	
	/**
	 * This getAllEmployeeDetails method is used to get all the Employees in the Database.
	 * @return This method will return the list of Employees if authenticated successfully.NoSuchEmployeeException in case there is no Employee in DB.
	 */
	public List<Employee> getAllEmployeeDetails();
	
	/**
	 * This approveDonation method is used to approve the donation to NeedyPeople.
	 * @param distribution it is the object of Distribution to which the donation is done.
	 * @return This method will return DonationDistribution Object if authenticated successfully.NoSuchDistributionException in case there is no Distribution in DB.
	 */
	public String approveOrRejectDonation(long distributionDistributionId,String donationBoxName);
}
