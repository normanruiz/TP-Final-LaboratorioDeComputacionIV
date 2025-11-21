package dominio.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import dominio.entity.Enums.PROFILE;
import dominio.entity.Enums.SEX;


public class Client {
	
	private int id;
	private String usr;
	private String pwd;
	private PROFILE profile;
	private boolean status;
	private String DNI;
	private String CUIL;
	private String name;
	private String lastName;
	private SEX sex;
	private LocalDate birthdate;
	private String nationality;
	private String address;
	private String city;
	private String state;
	private String eMail;
	private String phone;
	private ArrayList<BankAccount> bankAccounts;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public Client(int id, String usr, String pwd, boolean status, String dni, String cuil, String name,
			String lastName, SEX sex, String nationality, LocalDate birthdate, String address, String city,
			String state, String eMail, String phone, ArrayList<BankAccount> bankAccounts, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.usr = usr;
		this.pwd = pwd;
		this.profile = PROFILE.CLIENT;
		this.status = status;
		this.DNI = dni;
		this.CUIL = cuil;
		this.name = name;
		this.lastName = lastName;
		this.sex = sex;
		this.nationality = nationality;
		this.birthdate = birthdate;
		this.address = address;
		this.city = city;
		this.state = state;
		this.eMail = eMail;
		this.phone = phone;
		this.bankAccounts = bankAccounts;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Client() {
		this(-1, null, null, true, null, null, null, null, SEX.MALE, null, null, null, null, null, null, null, null, null, null);
	}
	
	
	public Client(Client client) {
		super();
		this.id = client.id;
		this.usr = client.usr;
		this.pwd = client.pwd;
		this.profile = PROFILE.CLIENT;
		this.status = client.status;
		this.DNI = client.DNI;
		this.CUIL = client.CUIL;
		this.name = client.name;
		this.lastName = client.lastName;
		this.sex = client.sex;
		this.nationality = client.nationality;
		this.birthdate = client.birthdate;
		this.address = client.address;
		this.city = client.city;
		this.state = client.state;
		this.eMail = client.eMail;
		this.phone = client.phone;
		this.bankAccounts = client.bankAccounts;
		this.createdAt = client.createdAt;
		this.updatedAt = client.updatedAt;
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

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getCUIL() {
		return CUIL;
	}

	public void setCUIL(String cUIL) {
		CUIL = cUIL;
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

	public SEX getSex() {
		return sex;
	}

	public void setSex(SEX sex) {
		this.sex = sex;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public ArrayList<BankAccount> getBankAccounts() {
		return this.bankAccounts;
	}

	public void setBankAccounts(ArrayList<BankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
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

	public PROFILE getProfile() {
		return profile;
	}

	@Override
	public String toString() {
		return  id + ", usr=" + usr + ", pwd=" + pwd + ", profile=" + profile + ", status=" + status
				+ ", DNI=" + DNI + ", CUIL=" + CUIL + ", name=" + name + ", lastName=" + lastName + ", sex=" + sex
				+ ", nationality=" + nationality + ", birthdate=" + birthdate + ", address=" + address + ", city="
				+ city + ", state=" + state + ", eMail=" + eMail + ", phone=" + phone + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}
	
	public String getFullName() {
		return this.lastName + " " + this.name;
	}
}
