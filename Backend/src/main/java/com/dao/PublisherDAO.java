package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.model.Publisher;
@Repository
	public interface PublisherDAO extends JpaRepository<Publisher, Integer> {

	 Publisher findByName(String name);
	 List<Publisher> findByCity(String city);
	 List<Publisher> findByStateCode(String stateCode);
	}

