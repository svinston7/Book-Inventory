package com.Service;
 
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.dao.AuthorDAO;
import com.dao.BookAuthorDAO;
import com.dao.BookDAO;
import com.model.Author;
import com.model.Book;
import com.model.BookAuthor;
@Service
public class AuthorService {
	@Autowired
AuthorDAO authorDao;BookDAO bookDao;
	BookAuthorDAO bookauthorDao;
	public List<Author> getAll(){
		return authorDao.findAll();
	}
	public Author findById(int id) {
	    return authorDao.findById(id)
	                  .orElseThrow(() -> new RuntimeException("author not found with id: " + id));
	}
	public void removeAuthor(int id) {
		authorDao.deleteById(id);
	}
	public void addAuthor(Author author) {
		authorDao.save(author);
	}
	public Author findByFirstName(String firstname){
		return authorDao.findByFirstName(firstname);
	}
	public Author findByLastName(String lastname){
		return authorDao.findByLastName(lastname);
	}
	public String updateAuthorFirstName(int id,String firstname) {
		Author author=authorDao.findById(id).orElse(null);
		if(author!=null) {
			author.setFirstName(firstname);
			authorDao.save(author);
			return "updated sucessfully";
		}
		return "author with"+firstname+" not found";
	}
	public String updateAuthorLastName(int id,String lastname) {
		Author author=authorDao.findById(id).orElse(null);
		if(author!=null) {
			author.setLastName(lastname);
			authorDao.save(author);
			return "updated sucessfully";
		}
		return "author with " +lastname+"not found";
	}
	public List<Book> getBooksByAuthor(int authorId){
		List<BookAuthor> bookAuthors=bookauthorDao.findByAuthorId(authorId);
		List<String> isbn=bookAuthors.stream().map(BookAuthor::getIsbn).collect(Collectors.toList());
		return bookDao.findByIsbn(isbn);
				
	}
}