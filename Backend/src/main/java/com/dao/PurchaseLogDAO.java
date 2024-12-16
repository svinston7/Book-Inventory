package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.model.PurchaseLog;
@Repository
	public interface PurchaseLogDAO extends JpaRepository<PurchaseLog, Integer> {
	}

