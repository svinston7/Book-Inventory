package com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Service.UserService;
import com.dao.UserDAO;
import com.model.User;

@RestController

public class UserController {
    
    private final UserDAO userdao;

	private final UserService userService;
	
	
	public UserController(UserDAO userdao, UserService userService) {
		super();
		this.userdao = userdao;
		this.userService = userService;
	}

	/*@GetMapping("/{userId}")
	public User getUserById(@PathVariable("userId") int id) {
	    return userService.findById(id); 
	}
	
	@PostMapping("/post")
	public ResponseEntity<?> addUser(@RequestBody User user) {
		userService.addUser(user);
		return new ResponseEntity<>(user+"Added",HttpStatus.OK);
	}*/
	@PostMapping("/register")
	public User register(@RequestBody User user) {
		if (user.getUserName() == null || user.getPassword() == null) {
            throw new IllegalArgumentException("Username and password are required");
        }
		return userService.register(user);
	}
	 @PostMapping("/login")
	    public String login(@RequestBody User user) {
	        
	        
	            return userService.verify( user);
	        
	        
	    }
	 @GetMapping("/getmessage")
		public String getMessage(@RequestBody User user) 
		{ 
			
			
			
				 return "Hello World";
			
		
			
		}
	
//	@PutMapping("/update/firstname/{userId}")
//	public User updateFirstName(@PathVariable("userId") int id) {
//		
//	}
//	@PutMapping("/update/lastname/{userId}")
//	public User updateLastName(@PathVariable("userId") int id) {
//		
//	}
//	@PutMapping("/update/phonenumber/{userId}")
//	public User updatePhoneNumber(@PathVariable("userId") int id) {
//		
//	}
}
