package com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class State {
	@Id
	private String stateCode;
	private String stateName;
	
	
	public State(String stateCode, String stateName) {
		super();
		this.stateCode = stateCode;
		this.stateName = stateName;
	}
	
	
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	
	

}
