package com.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ReviewerDAO;

import com.model.Reviewer;

@Service
public class ReviewerService {
	@Autowired
	ReviewerDAO reviewerDao;
	
	public List<Reviewer> getAll(){
		return reviewerDao.findAll();
	}
	
	public Reviewer findById(int id) {
	    return reviewerDao.findById(id)
	                  .orElseThrow(() -> new RuntimeException("Reviewer not found with id: " + id));
	}

	public void removeReviewer(int id) {
		reviewerDao.deleteById(id);
	}
	public void addReviewer(Reviewer reviewer) {
		reviewerDao.save(reviewer);
	}
}
