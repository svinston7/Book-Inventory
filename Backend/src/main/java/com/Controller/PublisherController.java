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
import com.exception.CustomException;
import com.exception.Response;
import com.model.Publisher;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/publisher")
public class PublisherController {
	
	@Autowired
	PublisherService publisherService;

	@PostMapping("/post")
    public ResponseEntity<?> postPublisher(@RequestBody Publisher publisher) {
       
        Publisher existingPublisher = publisherService.findByName(publisher.getName());
        
        if (existingPublisher != null) {
            
            throw new CustomException("ADDFAILS", "Publisher already exists");
        }

        publisherService.addPublisher(publisher);

    
        Response successResponse = new Response("POSTSUCCESS", "Publisher added successfully");
        return new ResponseEntity<>(successResponse, HttpStatus.CREATED);
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
	public ResponseEntity<?> updatePublisherCity(@PathVariable int publisherId,String city){
		Publisher publisher = publisherService.findById(publisherId);
		publisher.setCity(city);
		publisherService.addPublisher(publisher);
		return new ResponseEntity<Publisher>(publisher,HttpStatus.OK);
	}
	@PutMapping("/update/name/{publisherId}")
	public ResponseEntity<?> updatePublisherName(@PathVariable int publisherId,String name){
		Publisher publisher = publisherService.findById(publisherId);
		publisher.setName(name);
		publisherService.addPublisher(publisher);
		return new ResponseEntity<Publisher>(publisher,HttpStatus.OK);
	}
	@PutMapping("/update/state/{publisherId}")
	public ResponseEntity<?> updatePublisherState(@PathVariable int publisherId,String state){
		Publisher publisher = publisherService.findById(publisherId);
		publisher.setStateCode(state);
		publisherService.addPublisher(publisher);
		return new ResponseEntity<Publisher>(publisher,HttpStatus.OK);
	}
}
