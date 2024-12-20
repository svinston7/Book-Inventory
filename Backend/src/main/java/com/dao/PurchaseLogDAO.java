package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.model.PurchaseLog;
@Repository
	public interface PurchaseLogDAO extends JpaRepository<PurchaseLog, Integer> {
	
	PurchaseLog findByUserId(int userId); 
}

