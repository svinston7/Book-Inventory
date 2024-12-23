package com.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BookAuthorDAO;
import com.dao.UserDAO;
import com.model.BookAuthor;
import com.model.User;
@Service
public class BookAuthorService {

	@Autowired
	BookAuthorDAO bookauthorDao;
	
	public List<BookAuthor> getAll(){
		return bookauthorDao.findAll();
	}
	
	public BookAuthor findById(int id) {
	    return bookauthorDao.findById(id)
	                  .orElseThrow(() -> new RuntimeException("Bookauthor not found with id: " + id));
	}

	public void removeBookAuthor(int id) {
		bookauthorDao.deleteById(id);
	}
	public void addBookAuthor(BookAuthor bookauthor) {
		bookauthorDao.save(bookauthor);
	}
}
