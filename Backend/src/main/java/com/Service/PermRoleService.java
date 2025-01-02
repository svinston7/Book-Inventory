package com.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.PermRoleDAO;
import com.dao.UserDAO;
import com.model.PermRole;
import com.model.User;

@Service
public class PermRoleService {
	@Autowired
	PermRoleDAO PermRoleDao;
	
	public List<PermRole> getAll(){
		return PermRoleDao.findAll();
	}
	
	public PermRole findById(int id) {
	    return PermRoleDao.findById(id)
	                  .orElseThrow(() -> new RuntimeException("PermRole not found with id: " + id));
	}

	public void removePermrole(int id) {
		PermRoleDao.deleteById(id);
	}
	public void addPermRole(PermRole PermRole) {
		PermRoleDao.save(PermRole);
	}

	public void updatePermRole(int i, String string) {
		PermRole role = PermRoleDao.findById(i).orElse(null);
		role.setPermRole(string);
		PermRoleDao.save(role);
	}
}
