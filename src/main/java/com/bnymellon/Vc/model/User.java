package com.bnymellon.Vc.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.springframework.beans.factory.annotation.Autowired;

@Entity(name="User")
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="UserId")
	private String UserId;
	private String Name;
	@Autowired
	@ElementCollection
	@OneToMany(cascade = CascadeType.ALL,mappedBy="Owner",orphanRemoval = true)
	private List<Vault> VaultList;
	
	public User() {
		super();
	}
	
	public User(String userId, String name) {
		super();
		UserId = userId;
		Name = name;
		VaultList=new ArrayList<Vault>();
	}
	
	
	public User(String userId, String name, List<Vault> vaultList) {
		super();
		UserId = userId;
		Name = name;
		VaultList = vaultList;
	}
	public User(String name) {
		super();
		Name=name;
		VaultList=new ArrayList<Vault>();
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public List<Vault> getVaultList() {
		return VaultList;
	}
	public void setVaultList(List<Vault> vaultList) {
		VaultList = vaultList;
	} 
	public void add(Vault vault) {
		VaultList.add(vault);
	}
	
	
	
}
