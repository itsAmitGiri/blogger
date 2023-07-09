package com.application.blogger.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@NoArgsConstructor
@Getter
@Setter
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	
	@Column(name="user_name", nullable = false)
	private String userName;
	
	@Column(name="email", nullable = false)
	private String email;
	
	
	private String password;
	
	
	private String about;
	
}
