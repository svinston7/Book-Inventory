package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.model.State;
@Repository
	public interface StateDAO extends JpaRepository<State, String> {
	}

