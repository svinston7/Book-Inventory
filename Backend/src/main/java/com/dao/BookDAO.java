package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.model.Book;
@Repository
	public interface BookDAO extends JpaRepository<Book,String> {

	Book findByTitle(String title);

	Book findByPublisherId(String publisherId);
	}

