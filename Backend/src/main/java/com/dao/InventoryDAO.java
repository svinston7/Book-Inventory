package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.model.Inventory;
@Repository
	public interface InventoryDAO extends JpaRepository<Inventory, Integer> {
	}

