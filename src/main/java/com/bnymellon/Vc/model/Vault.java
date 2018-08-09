package com.bnymellon.Vc.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name="Vault")
@Table(name="vault")
public class Vault {
	
	@ManyToOne(targetEntity=User.class,cascade = CascadeType.PERSIST,fetch=FetchType.LAZY)
	@JoinColumn(name="UserId")
	private User Owner;
	private String Name;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String VaultId;
	@ElementCollection
	private List<File> VaultFiles;
	
	public Vault() {
		super();
	}
	
	public Vault(User owner, String name) {
		super();
		
		Owner = owner;
		Name = name;
		VaultFiles=new ArrayList<File>();
	}
	
	public Vault(User owner,String name, List<File> vaultFiles) {
		super();
		
		Owner = owner;
		Name = name;
		VaultFiles = vaultFiles;
	}
	public Vault(String name,String vaultId,List<File> vaultFiles) {
		super();
		Name=name;
		VaultId=vaultId;
		VaultFiles=vaultFiles;
	}
	public User getOwner() {
		return Owner;
	}
	public void setOwner(User owner) {
		Owner = owner;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getVaultId() {
		return VaultId;
	}
	public void setVaultId(String vaultId) {
		VaultId = vaultId;
	}
	public List<File> getVaultFiles() {
		return VaultFiles;
	}
	public void setVaultFiles(List<File> vaultFiles) {
		VaultFiles = vaultFiles;
	}
	
	

}
