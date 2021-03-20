package com.paternocode.springbootbackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "heroes")
public class Hero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@NotEmpty
	@Column(name = "name")
	private String name;
	
	@NotNull
	@Column(name = "code", unique = true)
	private Integer code;
	
	@NotNull
	@Column(name = "role")
	private Role role;
	
	@Column(name = "specialty")
	private String specialty;
	
	@Column(name = "video_id")
	private String videoId;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	
	public Hero(String name, Integer code, Role role, String specialty, String videoId) {
		super();
		this.name = name;
		this.code = code;
		this.role = role;
		this.specialty = specialty;
		this.videoId = videoId;
	}

	public Hero() {
		super();
		// TODO Auto-generated constructor stub
	}
}
