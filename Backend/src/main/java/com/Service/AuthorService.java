package com.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AuthorDAO;
import com.model.Author;
@Service
public class AuthorService {
	@Autowired
AuthorDAO authorDao;
	
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
}
