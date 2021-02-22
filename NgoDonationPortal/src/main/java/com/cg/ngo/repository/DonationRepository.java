package com.cg.ngo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.ngo.beans.Donation;


@Repository
public interface DonationRepository extends JpaRepository<Donation, Integer> {

}
