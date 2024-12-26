package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.model.BookReview;
import com.model.Reviewer;
@Repository
	public interface BookReviewDAO extends JpaRepository<BookReview, Integer> {

	BookReview findByIsbn(String isbn);
	
	@Query("SELECT br FROM BookReview br WHERE br.isbn = :isbn")
	List<BookReview> findReviewsByIsbn(@Param("isbn") String isbn);
//	List<BookReview> findByIsbnIn(String isbn);
	}

