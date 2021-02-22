package com.cg.ngo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.ngo.beans.NeedyPeople;


@Repository
public interface NeedyPeopleRepository extends JpaRepository<NeedyPeople, Integer> {

	public NeedyPeople findByUserName(String userName);
	public NeedyPeople findByUserNameAndPassword(String username, String password);
	public NeedyPeople findByneedyPersonId(int needyPersonId);
	@Query(value = "Select needy from NeedyPeople needy where needyPersonName=:name")
	public List<NeedyPeople> findNeedyPeopleByName(String name);
}
