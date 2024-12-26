package com.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BookReviewDAO;
import com.dao.UserDAO;
import com.model.BookReview;
import com.model.Reviewer;

@Service
public class BookReviewService {
	@Autowired
	BookReviewDAO bookreviewDao;
	
	public List<BookReview> getAll(){
		return bookreviewDao.findAll();
	}
	
	public BookReview findByisbn(String isbn) {
	    return bookreviewDao.findByIsbn(isbn);
	                  
	}

	public List<BookReview> getAllReviewers(String isbn){
		return bookreviewDao.findReviewsByIsbn(isbn);
	}
	
	
	public void addBookReview(BookReview bookreview) {
		bookreviewDao.save(bookreview);
	}
}
