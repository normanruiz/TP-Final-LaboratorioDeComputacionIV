package dominio.entity;

import java.time.LocalDateTime;

import dominio.entity.Enums.PROFILE;

public class Admin {

	private int id;
	private String usr;
	private String pwd;
	private PROFILE profile;
	private boolean status;
	private String name;
	private String lastName;
	private String email;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public Admin(int id, String usr, String pwd, boolean status, String name, String lastName,
			String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.usr = usr;
		this.pwd = pwd;
		this.profile = PROFILE.ADMIN;
		this.status = status;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	public Admin(Admin admin) {
		super();
		this.id = admin.id;
		this.usr = admin.usr;
		this.pwd = admin.pwd;
		this.profile = PROFILE.ADMIN;
		this.status = admin.status;
		this.name = admin.name;
		this.lastName = admin.lastName;
		this.email = admin.email;
		this.createdAt = admin.createdAt;
		this.updatedAt = admin.updatedAt;
	}

	public Admin() {
		this(-1, null, null, true, null, null, null, null, null);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsr() {
		return usr;
	}

	public void setUsr(String usr) {
		this.usr = usr;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public PROFILE getProfile() {
		return profile;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
}
