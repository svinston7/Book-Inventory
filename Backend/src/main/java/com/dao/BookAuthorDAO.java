package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.model.BookAuthor;
@Repository
	public interface BookAuthorDAO extends JpaRepository<BookAuthor, Integer> {
	}

