package com.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BookReviewDAO;
import com.dao.UserDAO;
import com.model.BookReview;

@Service
public class BookReviewService {
	@Autowired
	BookReviewDAO bookreviewDao;
	
	public List<BookReview> getAll(){
		return bookreviewDao.findAll();
	}
	
//	public BookReview findByisbn(String isbn) {
//	    return bookreviewDao.findById(isbn)
//	                  .orElseThrow(() -> new RuntimeException("Bookreview not found with isbn: " + isbn));
//	}
//
//	public void removeBookReview(String isbn) {
//		bookreviewDao.deleteById(isbn);
//	}
	public void addBookReview(BookReview bookreview) {
		bookreviewDao.save(bookreview);
	}
}
