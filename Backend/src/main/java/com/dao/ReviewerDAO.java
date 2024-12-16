package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.model.Reviewer;
@Repository
	public interface ReviewerDAO extends JpaRepository<Reviewer, Integer> {
	}

