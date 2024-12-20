package com.Controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Service.PermRoleService;
import com.model.PermRole;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/permrole")
public class PermRoleController {
	
	@Autowired
	PermRoleService roleService;
	
	@PostMapping("/post")
	public ResponseEntity<?> postRole(@RequestBody PermRole role){
		roleService.addPermRole(role);
		return new ResponseEntity<PermRole>(role,HttpStatus.OK);
		
	}
	
	@GetMapping("/rolenumber/{rolenumber}")
	public ResponseEntity<?> getRole(@PathVariable int roleNumber){
		PermRole role = roleService.findById(roleNumber);
		return new ResponseEntity<PermRole>(role,HttpStatus.OK);

	}
	
	@PutMapping("/update/permrole/{rolenumber}")
	public ResponseEntity<?> updateRole(@PathVariable int roleNumber,String role){
		PermRole roleObj = roleService.findById(roleNumber);
		roleObj.setPermRole(role);
		roleService.addPermRole(roleObj);
		return new ResponseEntity<String>("Success",HttpStatus.OK);

	}

}
