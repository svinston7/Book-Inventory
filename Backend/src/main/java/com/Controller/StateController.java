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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Service.StateService;
import com.exception.InvalidInputException;
import com.exception.ResourceNotFoundException;
import com.exception.Response;
import com.model.State;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/state")
public class StateController {

	@Autowired
	StateService stateService;
	
	@PostMapping("/post")
	public ResponseEntity<?> postState(@RequestBody State state)throws InvalidInputException {
		try {
			stateService.addState(state);
	        return ResponseEntity.ok(new Response("POSTSUCCESS", "State added successfully"));
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(new Response("ADDFAILS", "An unexpected error occurred"));
	    }
		
	}
	
	@GetMapping("")
	public ResponseEntity<?> getAllState()throws InvalidInputException, ResourceNotFoundException {
		List<State> stateList = stateService.getAll();
		return new ResponseEntity<List<State>>(stateList,HttpStatus.OK);
	}
	
	@GetMapping("{stateId}")
	public ResponseEntity<?> getState(@PathVariable String stateId)throws InvalidInputException, ResourceNotFoundException {
		State state = stateService.findByCode(stateId);
		return new ResponseEntity<State>(state,HttpStatus.OK);
	}
	
	@PutMapping("/update/{stateId}")
	public ResponseEntity<?> updateState(@PathVariable String stateId,@RequestBody String stateName){
		State state = stateService.findByCode(stateId);
		state.setStateName(stateName);
		stateService.addState(state);
		return new ResponseEntity<State>(state,HttpStatus.OK);
	}
}
