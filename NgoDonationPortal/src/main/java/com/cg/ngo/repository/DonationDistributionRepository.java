package com.cg.ngo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.cg.ngo.beans.DonationDistribution;
import com.cg.ngo.beans.Employee;
import com.cg.ngo.beans.NeedyPeople;
@Repository
public interface DonationDistributionRepository extends JpaRepository<DonationDistribution,Long>{
	
	@Query(value="Select e from DonationDistribution e Where needyPersonUserName=:userName")
	public DonationDistribution helpNeedyPerson(String userName);

}
