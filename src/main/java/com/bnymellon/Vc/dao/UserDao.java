package com.bnymellon.Vc.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bnymellon.Vc.model.User;
import com.bnymellon.Vc.model.Vault;
import com.bnymellon.Vc.repository.VcRepository;

@Service
public class UserDao {
	@PersistenceContext protected EntityManager entityManager;
	@Autowired
	private VcRepository vcRepository;
	
	public List<User> getAllUser(){
		return (List<User>)vcRepository.findAll();
	}
	public User getUser(String userId) {
		List<User> userList=getAllUser();
		User tempUser=null;
		for(User user:userList) {
			if(user.getUserId().equals(userId)) {
				tempUser=user;
				break;
			}
		}
		return tempUser;
	}
	public void addUser(User user) {
		vcRepository.save(user);
		String s="C:\\vc\\"+user.getUserId();
		new File(s).mkdirs();
	}
	
	@Transactional
	public void addVault(String userId,Vault vault) {
		User tempUser=getUser(userId);
		Vault newvault=new Vault(tempUser,vault.getName(),vault.getVaultFiles());
		tempUser.add(newvault);
		entityManager.persist(tempUser);
		String s="C:\\vc\\"+userId+"\\"+vault.getVaultId();
		System.out.println(s);
		new File(s).mkdirs();
	}
	@Transactional
	public void deleteVault(String userId,String vaultId) {
		User tempUser = getUser(userId);
		for(Vault vault:tempUser.getVaultList()) {
			if(vault.getVaultId().equals(vaultId)) {
				tempUser.getVaultList().remove(vault);
				break;
			}
		}
		entityManager.persist(tempUser);
		String s="C:\\vc\\"+userId+"\\"+vaultId;
		Path directory=Paths.get(s);
		try {
			Files.walkFileTree(directory,new SimpleFileVisitor<Path>() {
				   @Override
				   public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
				       Files.delete(file); // this will work because it's always a File
				       return FileVisitResult.CONTINUE;
				   }
				   @Override
				   public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
				       Files.delete(dir); //this will work because Files in the directory are already deleted
				       return FileVisitResult.CONTINUE;
				   }
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void updateVault(String userId,String vaultId,Vault vault) {
		User tempUser=getUser(userId);
		for(Vault tempVault:tempUser.getVaultList()) {
			if(tempVault.getVaultId().equals(vaultId)) {
				tempVault=new Vault(tempUser,vault.getName(),vault.getVaultFiles());
				entityManager.persist(tempVault);
				break;
			}
		}
		entityManager.persist(tempUser);
	}
	
	public Vault getVault(String userId,String vaultId) {
		User tempUser=getUser(userId);
		for(Vault vault:tempUser.getVaultList()) {
			if(vault.getVaultId().equals(vaultId)) {
				return vault;
			}
		}
		return null;
	}
	
}
