package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.model.Publisher;
@Repository
	public interface PublisherDAO extends JpaRepository<Publisher, Integer> {
	}

