package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.model.BookReview;
@Repository
	public interface BookReviewDAO extends JpaRepository<BookReview, String> {
	}

