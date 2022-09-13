package com.dailymeows.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Username is required.")
	@Size(min = 6, message = "Username must be at least six (6) characters.")
	@Size(max = 32, message = "Username cannot exceed thirty-two (32) characters.")
	private String username;
	
	@NotEmpty(message = "Email is required.")
	@Email(message = "Email must be a valid email format tacocat@dailymeows.com.")
	private String email;
	
	@NotEmpty(message = "Password is required.")
	@Size(min = 8, message = "Password must be at least eight (8) characters.")
	private String password;
	
	@Transient
	@NotEmpty (message = "Confirm Password is required.")
	@Size(min = 8, message = "Password Confirmation must match Password.")
	private String confirmPassword;
	
	private boolean meowsletter;

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;
	
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
		private List<Meow> meows;
	
	public User() {
	}
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public boolean isMeowsletter() {
		return meowsletter;
	}

	public void setMeowsletter(boolean meowsletter) {
		this.meowsletter = meowsletter;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Meow> getMeows() {
		return meows;
	}

	public void setMeows(List<Meow> meows) {
		this.meows = meows;
	}

	
	
	
	
}