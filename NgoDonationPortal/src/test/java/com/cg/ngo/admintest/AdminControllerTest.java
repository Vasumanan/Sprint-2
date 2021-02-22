package com.cg.ngo.admintest;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cg.ngo.beans.Address;
import com.cg.ngo.beans.Employee;
import com.cg.ngo.exceptions.DuplicateEmployeeException;
import com.cg.ngo.exceptions.NoSuchAdminException;
import com.cg.ngo.exceptions.NoSuchEmployeeException;
import com.cg.ngo.services.AdminServiceImpl;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdminControllerTest {
	@Autowired
	private TestRestTemplate restTemplate;
	@MockBean                           
	private AdminServiceImpl adminService; 
	@LocalServerPort
	private int port;
	private Employee employeeMock1;
	private Employee employeeMock2;
	@BeforeEach
	public void setUpMockData() {
		employeeMock1 = new Employee("Vasumanan J","vasu@gmail.com","8940823277","Vasu","vasu@123",new Address("Coimbatore","TamilNadu","641201","Chettipalayam"));
		employeeMock2 = new Employee("Vinoth","vinoth@gmail.com","967710823277", "vinoth@123","Vinoth",new Address("Salem","TamilNadu","641204","Edapadi"));
	}
	@Test
	public void createEmployeeTestForValidCreate() {
		when(adminService.createEmployee(employeeMock1)).thenReturn(employeeMock1);
		ResponseEntity<Employee> postResponse=restTemplate.postForEntity("http://localhost:"+port+"/saveEmployee",employeeMock1,Employee.class);
		assertNotNull(postResponse);
		assertEquals(employeeMock1,postResponse.getBody());
		assertThat(postResponse.getStatusCode(), is(HttpStatus.CREATED));
	}
	@Test
	public void createEmployeeTestForDuplicateEmployeeException() {
		when(adminService.createEmployee(employeeMock1)).thenThrow(new DuplicateEmployeeException("Employee exist"));
		ResponseEntity<Employee> getResponse=restTemplate.postForEntity("http://localhost:"+port+"/saveEmployee",employeeMock1,Employee.class);
		assertThat(getResponse.getStatusCode(), is(HttpStatus.BAD_REQUEST));
	}
	@Test
	public void testGetAllEmployeeDetailsForValidList() {
		when(adminService.getAllEmployeeDetails()).thenReturn(Stream.of(employeeMock1,employeeMock2).collect(Collectors.toList()));
		ResponseEntity<List<Employee>> getResponse = restTemplate.exchange("http://localhost:"+port+"/findAllEmployee", HttpMethod.GET,null,new ParameterizedTypeReference<List<Employee>>() {});
		assertEquals(2,getResponse.getBody().size());
		assertThat(getResponse.getStatusCode(), is(HttpStatus.FOUND));
		
	}
	@Test
	public void testFindEmployeeDetailsByNameForValidList() {
		String employeeName = "Vasumanan J";
		when(adminService.getEmployeeDetailsByName(employeeName)).thenReturn(Stream.of(employeeMock1).collect(Collectors.toList()));
		ResponseEntity<List<Employee>> getResponse = restTemplate.exchange("http://localhost:"+port+"/findAllEmployeesByName/{employeeName}", HttpMethod.GET,null,new ParameterizedTypeReference<List<Employee>>() {},employeeName);
		assertNotNull(getResponse.getBody());
		assertThat(getResponse.getStatusCode(), is(HttpStatus.FOUND));
		assertTrue(getResponse.getBody().contains(employeeMock1));
	}
	@Test
	public void testFindEmployeeByEmployeeIdForValidEmployee() {
		long employeeId=1;
		when(adminService.getEmployeeDetailsById(employeeId)).thenReturn(employeeMock1);
		ResponseEntity<Employee> getResponse=restTemplate.getForEntity("http://localhost:"+port+"/findEmployee/{employeeId}",Employee.class,employeeId);
		assertNotNull(getResponse.getBody());
		assertEquals(employeeMock1,getResponse.getBody());
		assertThat(getResponse.getStatusCode(), is(HttpStatus.FOUND));
	}
	@Test
	public void testFindEmployeeByEmployeeIdForNoSuchEmployeeExeption() {
		long employeeId=12;
		when(adminService.getEmployeeDetailsById(employeeId)).thenThrow(new NoSuchEmployeeException("Employee not found"));
		ResponseEntity<Employee> getResponse=restTemplate.getForEntity("http://localhost:"+port+"/findEmployee/{employeeId}",Employee.class,employeeId);
		assertThat(getResponse.getStatusCode(), is(HttpStatus.NOT_FOUND));
	}
	
	@Test
	public  void testAdminLoginForValidUserName() {
		String userName = "Vasu";
		String password = "vasu@123";
		when(adminService.login(userName, password)).thenReturn("login Successful");
		ResponseEntity<String> getResponse=restTemplate.getForEntity("http://localhost:"+port+"/login/{userName}/{password}",String.class,userName,password);
		assertEquals("login Successful",getResponse.getBody().toString());
	}

	@Test
	public  void testAdminLoginForNoSuchAdminException() {
		String userName = "Vasumanan";
		String password = "vasu@123";
		when(adminService.login(userName, password)).thenThrow(new NoSuchAdminException("Admin not found"));
		ResponseEntity<?> getResponse=restTemplate.getForEntity("http://localhost:"+port+"/login/{userName}/{password}",String.class,userName,password);
		assertThat(getResponse.getStatusCode(), is(HttpStatus.NOT_FOUND));
	}

}
