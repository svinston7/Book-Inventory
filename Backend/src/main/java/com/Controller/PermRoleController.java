package com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Service.PermRoleService;
import com.exception.InvalidInputException;
import com.exception.ResourceNotFoundException;
import com.exception.Response;
import com.model.PermRole;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/permrole")
public class PermRoleController {
	
	@Autowired
	PermRoleService roleService;
	
	@PostMapping("/post")
	public ResponseEntity<?> postRole(@RequestBody PermRole role)throws InvalidInputException {
		try {
			roleService.addPermRole(role);
	        return ResponseEntity.ok(new Response("POSTSUCCESS", "Role added successfully"));
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(new Response("ADDFAILS", "An unexpected error occurred"));
	    }
		
	}
	
	@GetMapping("/rolenumber/{roleNumber}")
	@PreAuthorize("hasRole('ROLE_Admin')")
	public ResponseEntity<?> getRole(@PathVariable int roleNumber)throws InvalidInputException, ResourceNotFoundException {
		PermRole role = roleService.findById(roleNumber);
		return new ResponseEntity<PermRole>(role,HttpStatus.OK);

	}
	
	@PutMapping("/update/permrole/{rolenumber}")
	public ResponseEntity<?> updateRole(@PathVariable int roleNumber,@RequestBody String role)throws InvalidInputException, ResourceNotFoundException {
		PermRole roleObj = roleService.findById(roleNumber);
		roleObj.setPermRole(role);
		roleService.addPermRole(roleObj);
		return new ResponseEntity<String>("Success",HttpStatus.OK);

	}

}
