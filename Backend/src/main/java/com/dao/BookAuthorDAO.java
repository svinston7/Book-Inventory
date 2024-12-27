package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.model.BookAuthor;
import com.model.*;
@Repository
	public interface BookAuthorDAO extends JpaRepository<BookAuthor,Integer> {


	List<BookAuthor> findByAuthorId(int authorId);
	List<BookAuthor> findByIsbn(String isbn);


	}

