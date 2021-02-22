package com.cg.ngo.employeetest;
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
import com.cg.ngo.beans.NeedyPeople;
import com.cg.ngo.exceptions.NoSuchNeedyPersonException;
import com.cg.ngo.repository.NeedyPeopleRepository;
import com.cg.ngo.services.EmployeeServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {
	private NeedyPeople needyPeopleMock1;
	private NeedyPeople needyPeopleMock2;
	@Autowired
	private EmployeeServiceImpl employeeService;
	@MockBean
	private NeedyPeopleRepository needyPeopleRepository;
	
	@BeforeEach
	public void setUpMockData() {
	    needyPeopleMock1 = new NeedyPeople("harika11","harika","Harika","9563214785",85236,new Address("Ravulaplem","AP","589632","Temple"));
		needyPeopleMock2=new NeedyPeople("bhargavi12","bhargavi","Bhargavi","99985632144",28000,new Address("Rajahmundry","AP","587412","Dmart"));
	}
	@Test
	public void findNeedyPeopleByNameTest()  
	{
	   when(needyPeopleRepository.findNeedyPeopleByName(needyPeopleMock1.getNeedyPersonName())).thenReturn(Stream.of(needyPeopleMock1).collect(Collectors.toList()));
	   assertEquals(employeeService.getNeedyPeopleByName(needyPeopleMock1.getNeedyPersonName()).size(),1);
	}
	@Test
	public void viewAllNeedyPeopleTestForValidList() 
	{ 
		when(needyPeopleRepository.findAll()).thenReturn(Stream.of(needyPeopleMock1,needyPeopleMock2).collect(Collectors.toList()));
		assertEquals(employeeService.getAllNeedyPeople().size(),2);
	
	}
	@Test
	public void viewAllNeedyPeopleTestForNoSuchNeedyPersonException() {
		when(needyPeopleRepository.findAll()).thenThrow(new NoSuchNeedyPersonException("No Needy People Data found"));		
		assertThrows(NoSuchNeedyPersonException.class, () -> employeeService.getAllNeedyPeople());
	}
}