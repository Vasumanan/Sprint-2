package com.cg.ngo.employeetest;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Assert;
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
import com.cg.ngo.beans.NeedyPeople;
import com.cg.ngo.services.EmployeeServiceImpl;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {
	@Autowired
	private TestRestTemplate restTemplate;
	@MockBean                           
	private EmployeeServiceImpl employeeService; 
	@LocalServerPort
	private int port;
	private NeedyPeople needyPeopleMock1;
	private NeedyPeople needyPeopleMock2;
	@BeforeEach
	public void setUpMockData() {
	    needyPeopleMock1 = new NeedyPeople("harika11","harika","Harika","9563214785",85236,new Address("Ravulaplem","AP","589632","Temple"));
		needyPeopleMock2=new NeedyPeople("bhargavi12","bhargavi","Bhargavi","99985632144",28000,new Address("Rajahmundry","AP","587412","Dmart"));
	}
	@Test
	public void testGetAllNeedyPeopleDetailsForValidList() {
		when(employeeService.getAllNeedyPeople()).thenReturn(Stream.of(needyPeopleMock1,needyPeopleMock2).collect(Collectors.toList()));
		ResponseEntity<List<NeedyPeople>> getResponse = restTemplate.exchange("http://localhost:"+port+"/getAllNeedyPeople", HttpMethod.GET,null,new ParameterizedTypeReference<List<NeedyPeople>>() {});
		assertEquals(2,getResponse.getBody().size());
		assertThat(getResponse.getStatusCode(), is(HttpStatus.OK));
		
	}
	@Test
	public void testFindNeedyPeopleByNameForValidList() {
		ResponseEntity<List<NeedyPeople>> getResponse = restTemplate.exchange("http://localhost:"+port+"/getNeedyPeople/{needyPersonName}", HttpMethod.GET,null,new ParameterizedTypeReference<List<NeedyPeople>>() {},needyPeopleMock1.getNeedyPersonName());
		Assert.assertNotNull(getResponse.getBody());
		assertThat(getResponse.getStatusCode(), is(HttpStatus.OK));
	}
    @Test
	public void testFindNeedyPersonByIdForValidNeedyPerson() {
		when(employeeService.getNeedyPeopleById(needyPeopleMock1.getNeedyPersonId())).thenReturn(needyPeopleMock1);
		ResponseEntity<NeedyPeople> getResponse=restTemplate.getForEntity("http://localhost:"+port+"/getNeedyPerson/{needyPersonId}",NeedyPeople.class,needyPeopleMock1.getNeedyPersonId());
		Assert.assertNotNull(getResponse.getBody());
		assertThat(getResponse.getStatusCode(), is(HttpStatus.OK));
	}
}
