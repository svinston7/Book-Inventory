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
	
	public Reviewer findById(int reviewerId) {
	    return reviewerDao.findById(reviewerId)
	                  .orElseThrow(() -> new RuntimeException("Reviewer not found with id: " + reviewerId));
	}
	public String updatereviewerFirstName(int reviewerId,String name) {
		Reviewer reviewer=reviewerDao.findById(reviewerId).orElse(null);
		reviewer.setName(name);
		reviewerDao.save(reviewer);
		return "updated sucessfully";
	}
	public String updateReviewerEmployedId(int id,String employedBy) {
		Reviewer reviewer=reviewerDao.findById(id).orElseThrow(() -> new RuntimeException("Reviewer not found with id: " + id));;
		System.out.println(reviewer.getEmployedBy());
		reviewer.setEmployedBy(employedBy);
		System.out.println(reviewer.getEmployedBy());

		reviewerDao.save(reviewer);
		return "updated sucessfully";
		
		

	}
	public void removeReviewer(int id) {
		reviewerDao.deleteById(id);
	}
	public void addReviewer(Reviewer reviewer) {
		reviewerDao.save(reviewer);
	}
}
