package com.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CategoryDAO;
import com.dao.UserDAO;
import com.model.Category;
import com.model.User;
@Service
public class CategoryService {
	@Autowired
	CategoryDAO categoryDao;
	
	public List<Category> getAll(){
		return categoryDao.findAll();
	}
	
	public Category findById(int id) {
	    return categoryDao.findById(id)
	                  .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
	}

	public void removeCategory(int id) {
		categoryDao.deleteById(id);
	}
	public void addCategory(Category category) {
		categoryDao.save(category);
	}
	
	public Category updateDescription(int catId) {
		//Category category=
	}
}
