

package com.cg.ngo.admintest;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.cg.ngo.beans.Address;
import com.cg.ngo.beans.Admin;
import com.cg.ngo.beans.Employee;
import com.cg.ngo.exceptions.DuplicateEmployeeException;
import com.cg.ngo.exceptions.NoSuchAdminException;
import com.cg.ngo.exceptions.NoSuchEmployeeException;
import com.cg.ngo.repository.AdminRepository;
import com.cg.ngo.repository.EmployeeRepository;
import com.cg.ngo.services.AdminService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceTest 
{
	private Employee employeeMock1;
	private Employee employeeMock2;
	
	@Autowired
	private AdminService adminService;
	@MockBean
	private EmployeeRepository employeeRepository;
	@MockBean
	private AdminRepository adminRepository;
	@BeforeEach
	public void setUpMockData() {
		employeeMock1 = new Employee("Vasumanan J","vasu@gmail.com","8940823277","Vasu","vasu@123",new Address("Coimbatore","TamilNadu","641201","Chettipalayam"));
		employeeMock2 = new Employee("Vinoth","vinoth@gmail.com","967710823277", "vinoth@123","Vinoth",new Address("Salem","TamilNadu","641204","Edapadi"));
	}
	@Test
	public void testAddEmployeeForValidSave() {
		when(employeeRepository.save(employeeMock1)).thenReturn(employeeMock1);
		Employee result = adminService.createEmployee(employeeMock1);
		assertEquals(result.getEmployeeId(),employeeMock1.getEmployeeId());
	}

	@Test
	public void testAddEmployeeForDuplicateEmployeeException() {
		when(employeeRepository.save(employeeMock1)).thenThrow(new DuplicateEmployeeException("Employee already exist."));
		assertThrows(DuplicateEmployeeException.class, () -> adminService.createEmployee(employeeMock1));
	}

	@Test
	public void testFindAllEmployeeDetailsForValidList() {
		when(employeeRepository.findAll()).thenReturn(Stream.of(employeeMock1,employeeMock2).collect(Collectors.toList()));
		assertEquals(2,adminService.getAllEmployeeDetails().size());
	}
	
	@Test
	public void testFindAllEmployeeDetailsForNoSuchEmployeeException() {
		when(employeeRepository.findAll()).thenThrow(new NoSuchEmployeeException("No Employee Data found"));		
		assertThrows(NoSuchEmployeeException.class, () -> adminService.getAllEmployeeDetails());
	}
	
	@Test
	public void testFindEmployeeDetailsByNameForValidList() {
		when(employeeRepository.findAllByName(employeeMock1.getEmployeeName())).thenReturn(Stream.of(employeeMock1).collect(Collectors.toList()));
		assertEquals(1,adminService.getEmployeeDetailsByName(employeeMock1.getEmployeeName()).size());
	}
	
	@Test
	public void testAdminLoginForValidUserName() {
		Admin admin = new Admin("Vasu","vasu@123");
		when(adminRepository.adminLogin(admin.getUsername(),admin.getPassword())).thenReturn(admin);
		assertEquals("login Successful",adminService.login("Vasu","vasu@123"));
	}
	
	@Test
	public void testAdminLoginForInValidUserName() {
		Admin admin = new Admin("Vasu","vasu@123");
		when(adminRepository.adminLogin(admin.getUsername(),admin.getPassword())).thenThrow(new NoSuchAdminException("Invalid UserName and Password."));;
		assertThrows(NoSuchAdminException.class, () -> adminService.login("Vasu","vasu@123"));
	}
}
