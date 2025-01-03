package com.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CategoryDAO;
import com.model.Category;
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

	public Category updateDescription(int catId,String catDescription) {
		Category category=categoryDao.findById(catId).orElse(null);
		if (category == null) {
	       
	        throw new RuntimeException("Category with ID " + catId + " not found.");
		}
		category.setCatDescription(catDescription);
		categoryDao.save(category);
		return category;
	}

}
