package com.cg.ngo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ngo.beans.DonationBox;

@Repository
public interface DonationBoxRepository extends JpaRepository<DonationBox,String>{

}
