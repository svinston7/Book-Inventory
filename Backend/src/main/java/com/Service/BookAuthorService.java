package com.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AuthorDAO;
import com.dao.BookAuthorDAO;
import com.dao.UserDAO;
import com.model.Author;
import com.model.BookAuthor;
import com.model.User;
@Service
public class BookAuthorService {

	@Autowired
	AuthorDAO authordao;
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
	public List<Author> getAuthorDetailsByIsbn(String isbn){
		List<BookAuthor> bookauthor=bookauthorDao.findByIsbn(isbn);
		List<Author> authors=new ArrayList<>();
		for(BookAuthor bookauthor1:bookauthor) {
			
			authordao.findById(bookauthor1.getAuthorId()).ifPresent(authors::add);
		}
		return  authors;
}
}

