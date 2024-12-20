package com.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Service.StateService;
import com.model.State;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/state")
public class StateController {

	@Autowired
	StateService stateService;
	
	@PostMapping("/post")
	public ResponseEntity<?> postState(@RequestBody State state){
		stateService.addState(state);
		return new ResponseEntity<>(state,HttpStatus.OK);
	}
	
	@GetMapping("")
	public ResponseEntity<?> getAllState(){
		List<State> stateList = stateService.getAll();
		return new ResponseEntity<List<State>>(stateList,HttpStatus.OK);
	}
	
	@GetMapping("{stateId}")
	public ResponseEntity<?> getState(@PathVariable String stateId){
		State state = stateService.findByCode(stateId);
		return new ResponseEntity<State>(state,HttpStatus.OK);
	}
	
	@PutMapping("/update/{stateId}")
	public ResponseEntity<?> updateState(@PathVariable String stateId,String stateName){
		State state = stateService.findByCode(stateId);
		state.setStateName(stateName);
		stateService.addState(state);
		return new ResponseEntity<State>(state,HttpStatus.OK);
	}
}
