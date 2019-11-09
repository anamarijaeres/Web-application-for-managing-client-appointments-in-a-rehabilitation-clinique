package hr.fer.spring.projekt_opp_proba.model;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="DoktoriTreneri")
public class Doctor_Coach {
	
	@Id
	@Column(name="korisnickoIme")
	private String dcUserName;
	
	@Column(name="Email")
	private String dcEmail;
	
	@Column(name="Slika")
	private Blob  dcImage;
	
	@Column(name="BrojKlijenata")
	private int usersRegisteredNum;
	
	@Column(name="MaxBrKlijenata")
	private int maxUsersRegisteredNum;
	
	@OneToOne
	@JoinColumn(name="korisnickoIme")
	private User user;
	
	public Doctor_Coach() {
		
	}

	public Doctor_Coach(String dcUserName, String dcEmail, Blob dcImage, int usersRegisteredNum,
			int maxUsersRegisteredNum) {
		super();
		this.dcUserName = dcUserName;
		this.dcEmail = dcEmail;
		this.dcImage = dcImage;
		this.usersRegisteredNum = usersRegisteredNum;
		this.maxUsersRegisteredNum = maxUsersRegisteredNum;
	}

	public String getDcUserName() {
		return dcUserName;
	}

	public void setDcUserName(String dcUserName) {
		this.dcUserName = dcUserName;
	}

	public String getDcEmail() {
		return dcEmail;
	}

	public void setDcEmail(String dcEmail) {
		this.dcEmail = dcEmail;
	}

	public Blob getDcImage() {
		return dcImage;
	}

	public void setDcImage(Blob dcImage) {
		this.dcImage = dcImage;
	}

	public int getUsersRegisteredNum() {
		return usersRegisteredNum;
	}

	public void setUsersRegisteredNum(int usersRegisteredNum) {
		this.usersRegisteredNum = usersRegisteredNum;
	}

	public int getMaxUsersRegisteredNum() {
		return maxUsersRegisteredNum;
	}

	public void setMaxUsersRegisteredNum(int maxUsersRegisteredNum) {
		this.maxUsersRegisteredNum = maxUsersRegisteredNum;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
