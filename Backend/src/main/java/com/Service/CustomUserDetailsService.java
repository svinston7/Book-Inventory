package com.Service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.app.BookInventory.CustomUserDetails;
import com.dao.UserDAO;
import com.model.User;



@Component
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
    private  UserDAO userRepository ;
	
	
	
	



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user=userRepository.findByUserName(username);
		if(Objects.isNull(user)) {
			System.out.println("User Not available");
			throw new UsernameNotFoundException("User not found");
		}
		return new CustomUserDetails(user);
	}


}
