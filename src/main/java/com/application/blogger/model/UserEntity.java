package com.application.blogger.model;

import java.util.ArrayList;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	private List<PostEntity> posts = new ArrayList<>();
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	private List<CommentEntity> comment = new ArrayList<>();
	
}






















