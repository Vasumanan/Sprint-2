package com.cg.ngo.needypeopletest;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
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
import com.cg.ngo.exceptions.DuplicateNeedyPersonException;
import com.cg.ngo.repository.NeedyPeopleRepository;

import com.cg.ngo.services.NeedyPeopleService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NeedyPeopleServiceTest 
{
	private NeedyPeople needyPeopleMock1;
	private NeedyPeople needyPeopleMock2;
	
	@Autowired
	private NeedyPeopleService needyPeopleService;
	
	@MockBean
	private NeedyPeopleRepository needyPeopleRepository;
	@BeforeEach
	public void setUpMockData() {
		needyPeopleMock1 = new NeedyPeople("shyam14","hihihi","shyam","9095102773",2000.00,new Address("Coimbatore","TamilNadu","641201","Chettipalayam"));
		needyPeopleMock2 = new NeedyPeople( "cibi123","hahaha","cibi","9677108232",4000.00,new Address("Salem","TamilNadu","641204","Edapadi"));
	}
	@Test
	public void testSaveNeedyPeopleForValidSave() {
		when(needyPeopleRepository.save(needyPeopleMock1)).thenReturn(needyPeopleMock1);
		NeedyPeople result = needyPeopleService.save(needyPeopleMock1);
		assertEquals(result.getNeedyPersonId(),needyPeopleMock1.getNeedyPersonId());
	}
	
	@Test
	public void testSaveNeedyPeopleForValidSave2() {
		when(needyPeopleRepository.save(needyPeopleMock2)).thenReturn(needyPeopleMock2);
		NeedyPeople result = needyPeopleService.save(needyPeopleMock2);
		assertEquals(result.getNeedyPersonId(),needyPeopleMock2.getNeedyPersonId());
	}

	@Test
	public void testSaveNeedyPeopleForDuplicateEmployeeException() {
		when(needyPeopleRepository.save(needyPeopleMock1)).thenThrow(new DuplicateNeedyPersonException("Employee already exist."));
		assertThrows(DuplicateNeedyPersonException.class, () -> needyPeopleService.save(needyPeopleMock1));
	}

}
