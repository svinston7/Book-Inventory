package com.Controller;

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

import com.Service.UserService;
import com.dao.UserDAO;
import com.exception.InvalidInputException;
import com.exception.ResourceNotFoundException;
import com.model.User;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private  UserDAO userdao;

    @Autowired
	private  UserService userService;
	


	@GetMapping("/{username}")
	public ResponseEntity<?>  getUserById(@PathVariable String username) {
	    User u= userService.findByUserName(username); 
	    return new ResponseEntity<>(u,HttpStatus.OK);
	}
	
	/*@PostMapping("/post")
	public ResponseEntity<?> addUser(@RequestBody User user) {
		userService.addUser(user);
		return new ResponseEntity<>(user+"Added",HttpStatus.OK);
	}*/
    
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody User user) {
		if (user.getUserName() == null || user.getPassword() == null) {
            throw new IllegalArgumentException("Username and password are required");
        }

		userService.register(user);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	 @PostMapping("/login")
	    public String login(@RequestBody User user) {
	        
	        
	            return userService.verify( user);
	        
	        
	    }
	 @PutMapping("/update/role/{userId}")
	 public void updateRole(@PathVariable int userId,@RequestBody int roleNumber)throws InvalidInputException, ResourceNotFoundException  {
		 
	 }
	
	 @PutMapping("/update/firstname/{userId}")
	 public ResponseEntity<?> updateFirstName(@PathVariable("userId") int userId, 
	                                             @RequestBody String firstName) {
	    
	         userService.updateUserFirstName(userId, firstName);  // Call service method to update firstName
	         return ResponseEntity.ok().build();  // Return 200 OK if successful
	     
	 }

	@PutMapping("/update/lastname/{userId}")
	public void updateLastName(@PathVariable int userId,@RequestBody String lastName) throws InvalidInputException, ResourceNotFoundException {
		userService.updateUserLastName(userId, lastName);
	}
	@PutMapping("/update/phonenumber/{userId}")
	public void updatePhoneNumber(@PathVariable int userId,@RequestBody String phonenumber) throws InvalidInputException, ResourceNotFoundException {
		userService.updateUserPhoneNum(userId, phonenumber);
	}
}
