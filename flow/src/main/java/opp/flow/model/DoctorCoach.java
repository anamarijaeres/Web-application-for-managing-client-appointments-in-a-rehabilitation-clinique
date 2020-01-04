package opp.flow.model;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

@Entity
public class DoctorCoach implements AppUser{
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	private boolean approvedByAdmin=false;



    @Column(unique = true, nullable = false)
    @NotNull
    private String username;
    
    private String password;
    
    private String firstname;
    
    private String lastname;
    
    private String role;
    
    @Lob
    private Byte[] image;
    
    private String email;
    
    private int maxNumClient;



    
    public DoctorCoach() {
    	
    }

	public DoctorCoach(Long id, boolean approvedByAdmin, @NotNull String username, String password, String firstname,
			String lastname, String role, Byte[] image, String email, int maxNumClient) {
		super();
		this.id = id;
		this.approvedByAdmin = approvedByAdmin;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.role = role;
		this.image = image;
		this.email = email;
		this.maxNumClient = maxNumClient;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isApprovedByAdmin() {
		return approvedByAdmin;
	}

	public void setApprovedByAdmin(boolean approvedByAdmin) {
		this.approvedByAdmin = approvedByAdmin;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Byte[] getImage() {
		return image;
	}

	public void setImage(Byte[] image) {
		this.image = image;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getMaxNumClient() {
		return maxNumClient;
	}

	public void setMaxNumClient(int maxNumClient) {
		this.maxNumClient = maxNumClient;
	}

	@Override
	public String toString() {
		return "DoctorCoach [id=" + id + ", approvedByAdmin=" + approvedByAdmin + ", username=" + username
				+ ", password=" + password + ", firstname=" + firstname + ", lastname=" + lastname + ", role=" + role
				+ ", image=" + Arrays.toString(image) + ", email=" + email + ", maxNumClient=" + maxNumClient + "]";
	}
	
	public void replaceAttributes(DoctorCoach update) throws IllegalArgumentException, IllegalAccessException {
    	Field[] fields = update.getClass().getDeclaredFields();
    	for(Field f:fields) {
    		if(f.get(update)!=null) {
    			f.set(this, f.get(update));
    		}
    	}
    }
}
