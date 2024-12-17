package com.app.BookInventory;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.model.User;

public class CustomUserDetails implements UserDetails{

	
	     private final User user;
	     
		
		public CustomUserDetails(User user) {
			super();
			this.user = user;
		}


		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			// TODO Auto-generated method stub
			return Collections.singleton(new SimpleGrantedAuthority("USER"));
		}


		@Override
		public String getPassword() {
			// TODO Auto-generated method stub
			return user.getPassword();
		}


		@Override
		public String getUsername() {
			// TODO Auto-generated method stub
			return user.getUserName();
		}
		@Override
		public boolean isAccountNonExpired() {
			return true;
		}
		@Override
		public boolean isAccountNonLocked() {
			return true;
		}
	    @Override
	    public boolean isCredentialsNonExpired() {
	    	return true;
	    }
		@Override
		public boolean isEnabled() {
			return true;
		}

	}

