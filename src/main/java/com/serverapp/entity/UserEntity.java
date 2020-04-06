package com.serverapp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "app_user")
public class UserEntity extends BaseEntity{

	@Id
	@SequenceGenerator(sequenceName = "user_seq",allocationSize = 1, name = "user_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq" )
	private Long id;
	
	private String userName;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String mobile;
	
	private int active;
	
	private String role;
}
