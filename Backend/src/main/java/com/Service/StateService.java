package com.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.StateDAO;

import com.model.State;
//import com.model.User;
@Service
public class StateService {
	@Autowired
StateDAO stateDao;
	
	public List<State> getAll(){
		return stateDao.findAll();
	}
	
	public State findByCode(String statecode) {
	    return stateDao.findById(statecode)
	                  .orElseThrow(() -> new RuntimeException("State not found with code: " +statecode ));
	}

	public void removeState(String statecode) {
		stateDao.deleteById(statecode);
	}
	public void addState(State state) {
		stateDao.save(state);
	}
}
