package com.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BookConditionDAO;

import com.model.BookCondition;
@Service
public class BookConditionService {
@Autowired
BookConditionDAO bookconditionDao;
	
	public List<BookCondition> getAll(){
		return bookconditionDao.findAll();
	}
	

	public BookCondition getBookCondition(int ranks) {
		return bookconditionDao.findByRanks(ranks);
		
		
	}
	public void updateDescription(int rank,String description) {
		BookCondition bookcondition=bookconditionDao.findByRanks(rank);
		bookcondition.setDescription(description);
	}
	public void updateFullDescription(int rank,String fulldescription) {
		BookCondition bookcondition=bookconditionDao.findByRanks(rank);
		bookcondition.setFullDescription(fulldescription);
		
	}
	public void updatePrice(int rank,double price) {
		BookCondition bookcondition=bookconditionDao.findByRanks(rank);
		bookcondition.setPrice(price);
	}
	public void addBookCondition(BookCondition bookcondition) {
		bookconditionDao.save(bookcondition);
	}
}
