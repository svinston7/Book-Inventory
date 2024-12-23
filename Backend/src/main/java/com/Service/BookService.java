package com.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BookDAO;

import com.model.Book;

@Service
public class BookService {
	
	@Autowired
	BookDAO bookDao;
	
	public List<Book> getAll(){
		return bookDao.findAll();
	}
	
	public Book findByIsbn(String isbn) {
	    return bookDao.findById(isbn)
	                  .orElseThrow(() -> new RuntimeException("Book not found with isbn: " + isbn));
	}
	
	public void updateBook(String isbn,Book updatedBook) {
		Book book = findByIsbn(isbn);
		book.setCategoryId(updatedBook.getCategoryId());
		book.setEdition(updatedBook.getEdition());
		book.setTitle(updatedBook.getTitle());
		book.setPublisherId(updatedBook.getPublisherId());
		bookDao.save(book);
	}

	public void removeBook(String isbn) {
		bookDao.deleteById(isbn);
	}
	public void addBook(Book book) {
		bookDao.save(book);
	}

	public Book findByTitle(String title) {
		return bookDao.findByTitle(title);
		
	}

	public List<Book> findByPublisherId(int publisherId) {
		
		return bookDao.findByPublisherId(publisherId);
	}
}
