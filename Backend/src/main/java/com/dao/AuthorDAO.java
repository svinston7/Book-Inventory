package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.model.Author;
@Repository
	public interface AuthorDAO extends JpaRepository<Author, Integer> {

	Author findByAuthorId(int id);
	
	Author findByFirstName(String firstname);

	Author findByLastName(String lastname);
	}

