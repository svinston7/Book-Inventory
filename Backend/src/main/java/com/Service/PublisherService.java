package com.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.PublisherDAO;
import com.model.Publisher;
@Service
public class PublisherService {
	@Autowired
	PublisherDAO publisherDao;
	
	public List<Publisher> getAll(){
		return publisherDao.findAll();
	}
	
	public Publisher findById(int id) {
	    return publisherDao.findById(id)
	                  .orElseThrow(() -> new RuntimeException("Publisher not found with id: " + id));
	}

	public void removePublisher(int id) {
		publisherDao.deleteById(id);
	}
	public void addPublisher(Publisher publisher) {
		publisherDao.save(publisher);
	}

	public Publisher findByName(String name) {
		
		return publisherDao.findByName(name);
	}
	
	public List<Publisher> findByState(String state) {
		return publisherDao.findByStateCode(state);
	}
	public List<Publisher> findByCity(String city) {
		return publisherDao.findByCity(city);
	}
}
