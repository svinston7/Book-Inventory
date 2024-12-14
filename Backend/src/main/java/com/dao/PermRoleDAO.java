package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.model.PermRole;
@Repository
	public interface PermRoleDAO extends JpaRepository<PermRole, Integer> {
	}

