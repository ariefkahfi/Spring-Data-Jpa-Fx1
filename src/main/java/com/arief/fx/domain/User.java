package com.arief.fx.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_user")
public class User {

	
	@Id
	@Column(name="id")
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name="uuid",strategy="org.hibernate.id.UUIDGenerator")
	private String id;
	
	@Column(name="username",nullable=false,unique=true,length=50)
	private String username;
	
	@Column(name="password",nullable=false)
	private String password;
	
	
	@OneToOne
	@JoinTable(name="user_mhs",joinColumns=@JoinColumn(name="id"),inverseJoinColumns=@JoinColumn(name="nim"))
	@Cascade(CascadeType.MERGE)
	private Mahasiswa mahasiswa;
	
	public User() {
		
	}
	
	
	
	public User(String username, String password, Mahasiswa mahasiswa) {
		this.username = username;
		this.password = password;
		this.mahasiswa = mahasiswa;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
