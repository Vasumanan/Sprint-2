package com.cg.ngo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.ngo.beans.DonationItem;

public interface DonationItemRepository extends JpaRepository<DonationItem,Integer>{

}
