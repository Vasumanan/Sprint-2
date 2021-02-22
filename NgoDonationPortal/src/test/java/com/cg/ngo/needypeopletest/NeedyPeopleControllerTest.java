package com.cg.ngo.needypeopletest;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cg.ngo.beans.Address;
import com.cg.ngo.beans.NeedyPeople;
import com.cg.ngo.services.NeedyPeopleServiceImpl;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NeedyPeopleControllerTest {
	@Autowired
	private TestRestTemplate restTemplate;
	@MockBean                           
	private NeedyPeopleServiceImpl needyPeopleService; 
	@LocalServerPort
	private int port;
	private NeedyPeople needyPeopleMock1;

	@BeforeEach
	public void setUpMockData() {
		needyPeopleMock1 = new NeedyPeople( "shyam14","hihihi","shyam","9095102773",2000.00,new Address("Coimbatore","TamilNadu","641201","Chettipalayam"));
	}
	@Test
	public void createNeedyPeopleTestForValidCreatee() {
		when(needyPeopleService.save(needyPeopleMock1)).thenReturn(needyPeopleMock1);
		ResponseEntity<NeedyPeople> postResponse = restTemplate.postForEntity("http://localhost:"+port+"/saveNeedyPeople", needyPeopleMock1, NeedyPeople.class);
		assertNotNull(postResponse.getBody());
		}
	
	
	@Test
	public void createNeedyPeopleTestForValidCreate() {
		when(needyPeopleService.save(needyPeopleMock1)).thenReturn(needyPeopleMock1);
		ResponseEntity<NeedyPeople> postResponse=restTemplate.postForEntity("http://localhost:"+port+"/saveNeedyPeople",needyPeopleMock1,NeedyPeople.class);
		assertNotNull(postResponse);
		assertEquals(needyPeopleMock1,postResponse.getBody());
		assertThat(postResponse.getStatusCode(), is(HttpStatus.CREATED));
		
	}	
	
	
	
	@Test
	public  void testNeedyPeopleLoginForValidUserName() {
		String userName = "shyam14";
		String password =  "string";
		when(needyPeopleService.login(userName, password)).thenReturn("login Successful");
		ResponseEntity<String> getResponse=restTemplate.getForEntity("http://localhost:"+port+"/login/{userName}/{password}",String.class,userName,password);
		assertEquals("login Successful",getResponse.getBody().toString());
		
	}
}
