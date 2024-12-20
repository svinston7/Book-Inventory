package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Author;
import com.model.Book;
@Repository
	public interface BookDAO extends JpaRepository<Book,String> {
	Author findByAuthorId(int id);
	List<Book> findByIsbn(List<String> isbn);
	Book findByTitle(String title);
	Book findByPublisherId(String publisherId);

	
	}

