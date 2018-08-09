package com.bnymellon.Vc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bnymellon.Vc.dao.UserDao;
import com.bnymellon.Vc.model.User;
import com.bnymellon.Vc.model.Vault;

@RestController
public class VcController {
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping("/vc")
	public List<User> getAllUsers(){
		return	(List<User>)userDao.getAllUser();
	}
	@RequestMapping("/vc/{userId}")
	public User getUser(@PathVariable String userId) {
		return userDao.getUser(userId);
	}
	@RequestMapping(method=RequestMethod.POST,value="/vc")
	public void addUser(@RequestBody User user) {
		userDao.addUser(user);
	}
	@RequestMapping(method=RequestMethod.PUT,value="/vc/{userId}")
	public void updateUser(@PathVariable String userId,@RequestBody User user) {
		userDao.addUser(user);
	}
	@RequestMapping(method=RequestMethod.POST,value="/vc/{userId}")
	public void addVault(@PathVariable String userId,@RequestBody Vault vault) {
		userDao.addVault(userId,vault);
	}
	@RequestMapping(method=RequestMethod.DELETE,value="/vc/{userId}/{vaultId}")
	public void removeVault(@PathVariable String userId,@PathVariable String vaultId){
		userDao.deleteVault(userId,vaultId);
	}
	@RequestMapping(method=RequestMethod.PUT,value="/vc/{userId}/{vaultId}")
	public void updateVault(@PathVariable String userId,@PathVariable String vaultId,@RequestBody Vault vault) {
		userDao.updateVault(userId,vaultId,vault);
	}
	@RequestMapping(method=RequestMethod.GET,value="/vc/{userId}/{vaultId}")
	public Vault getVault(@PathVariable String userId,@PathVariable String vaultId) {
		return userDao.getVault(userId,vaultId);
	}
	
}
