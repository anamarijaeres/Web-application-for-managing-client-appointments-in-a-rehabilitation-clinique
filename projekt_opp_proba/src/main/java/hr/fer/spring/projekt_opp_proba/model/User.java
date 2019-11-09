package hr.fer.spring.projekt_opp_proba.model;

import javax.persistence.*;

@Entity
@Table(name="Korisnik")
public class User {
	
	@Id
	@Column(name="korisnickoIme")
	private String userName;
	
	@Column(name="Lozinka")
	private String password;
	
	@Column(name="Ime")
	private String firstName;
	
	@Column(name="Prezime")
	private String lastName;
	
	@Column(name="Uloga")
	private String nameRole;
	
	@ManyToOne
	private Role role; 
	
	@OneToOne(mappedBy = "user")
	private Doctor_Coach doctorCoach;

	public User() {
		
	}

	public User(String userName, String password, String firstName, String lastName, String nameRole) {
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nameRole=nameRole;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNameRole() {
		return nameRole;
	}

	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Doctor_Coach getDoctorCoach() {
		return doctorCoach;
	}

	public void setDoctorCoach(Doctor_Coach doctorCoach) {
		this.doctorCoach = doctorCoach;
	}
}
