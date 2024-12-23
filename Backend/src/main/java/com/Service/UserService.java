package com.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

import com.model.User;
import com.dao.UserDAO;


@Service
public class UserService {
	@Autowired
	private  UserDAO userRepository;
	@Autowired
	private  BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private  AuthenticationManager authenticationManager;
	@Autowired
	private  JwtService jwtService;
	
	/*public UserService(UserDAO userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
			 AuthenticationManager authenticationManager,JwtService jwtService		) {
		super();
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.authenticationManager = authenticationManager;
		this.jwtService=jwtService;
	}*/
	public User register(User user) {
		// TODO Auto-generated method stub
		 if (user.getUserName() == null || user.getPassword() == null) {
		        throw new IllegalArgumentException("Username and password are required");
		    }
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
//	public String verify(User user) {
//		Authentication authenticate
//		=authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));
//		//var u=userRepository.findByUsername(user.getUsername());
//				if(authenticate.isAuthenticated()) {
//				  return jwtService.generateToken(user.getUserName());
//				}
//				return "failure";
//	}
	public String verify(User user) {
	    try {
	        Authentication authenticate = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword())
	        );
	        if (authenticate.isAuthenticated()) {
	            return jwtService.generateToken(user.getUserName());
	        }
	    } catch (RuntimeException e) {
	        // Handle authentication failure
	        return "failure";
	    }
	    return "failure";
	}

	
	
	public void addUser(User user) {
		userRepository.save(user);
	}
	public User getUser(int userId) {
		return userRepository.findById(userId).orElse(null);
	}
	public void updateUserFirstName(int userId,String firstname) {
		User user=userRepository.findById(userId).orElse(null);
		user.setFirstName(firstname);
		
		
		
		
		userRepository.save(user);
		
	}
	public void updateUserLastName(int userId,String lastname) {
		User user=userRepository.findById(userId).orElse(null);
		user.setLastName(lastname);
		
		
		
		
		userRepository.save(user);}
	
	public void updateUserPhoneNum(int userId,String phoneNumber) {
		User user=userRepository.findById(userId).orElse(null);
		user.setPhoneNumber(phoneNumber);
		
		
		
		
		userRepository.save(user);}
	

	
}
