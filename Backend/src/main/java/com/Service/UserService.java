package com.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.User;
import com.dao.UserDAO;

@Service
public class UserService {

	@Autowired
	UserDAO userDao;
	
	public List<User> getAll(){
		return userDao.findAll();
	}
	
	public User findById(int id) {
	    return userDao.findById(id)
	                  .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
	}

	
	public void addUser(User user) {
		userDao.save(user);
	}
}
