package com.example.mysse.mysse.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String name;

	@OneToMany(mappedBy = "writer")
	private List<Post> posts = new ArrayList<>();

	public User(Long id) {
		this.id = id;
	}

	public User(String name) {
		this.name = name;
	}
}
