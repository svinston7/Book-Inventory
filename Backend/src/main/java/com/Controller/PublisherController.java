package com.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Service.PublisherService;
import com.model.Publisher;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/publisher")
public class PublisherController {
	
	@Autowired
	PublisherService publisherService;

	@PostMapping("/post")
	public ResponseEntity<?> postBook(@RequestBody Publisher publisher){
		try {
			publisherService.addPublisher(publisher);
			return new ResponseEntity<>(Map.of("code", "POSTSUCCESS", "message", "Publisher added successfully"),HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(Map.of("code", "ADDFAILS", "message", "Book already exists"),HttpStatus.OK);
		}		
	}
	
	@GetMapping("")
	public ResponseEntity<?> getAllPublishers(){
		List<Publisher> publisherList = publisherService.getAll();
		return new ResponseEntity<List<Publisher>>(publisherList,HttpStatus.OK);
	}
	
	@GetMapping("/{publisherId}")
	public ResponseEntity<?> getPublisher(@PathVariable int publisherId){
		Publisher publisher = publisherService.findById(publisherId);
		return new ResponseEntity<Publisher>(publisher,HttpStatus.OK);
	}
	@GetMapping("/name/{name}")
	public ResponseEntity<?> getPublisherByName(@PathVariable String name){
		Publisher publisher = publisherService.findByName(name);
		return new ResponseEntity<Publisher>(publisher,HttpStatus.OK);
	}
	@GetMapping("/city/{city}")
	public ResponseEntity<?> getPublisherByCity(@PathVariable String city){
		List<Publisher> publisherList = publisherService.findByCity(city);
		return new ResponseEntity<List<Publisher>>( publisherList,HttpStatus.OK);
	}
	
	@GetMapping("/state/{state}")
	public ResponseEntity<?> getPublisherByState(@PathVariable String state){
		List<Publisher> publisherList = publisherService.findByState(state);
		return new ResponseEntity<List<Publisher>>( publisherList,HttpStatus.OK);
	}
	
	@PutMapping("/update/city/{publisherId}")
	public ResponseEntity<?> updatePublisherCity(@PathVariable int publisherId,@RequestBody String city){
		Publisher publisher = publisherService.findById(publisherId);
		publisher.setCity(city);
		publisherService.addPublisher(publisher);
		return new ResponseEntity<Publisher>(publisher,HttpStatus.OK);
	}
	@PutMapping("/update/name/{publisherId}")
	public ResponseEntity<?> updatePublisherName(@PathVariable int publisherId,@RequestBody String name){
		Publisher publisher = publisherService.findById(publisherId);
		publisher.setName(name);
		publisherService.addPublisher(publisher);
		return new ResponseEntity<Publisher>(publisher,HttpStatus.OK);
	}
	@PutMapping("/update/state/{publisherId}")
	public ResponseEntity<?> updatePublisherState(@PathVariable int publisherId,@RequestBody String state){
		Publisher publisher = publisherService.findById(publisherId);
		publisher.setStateCode(state);
		publisherService.addPublisher(publisher);
		return new ResponseEntity<Publisher>(publisher,HttpStatus.OK);
	}
}
