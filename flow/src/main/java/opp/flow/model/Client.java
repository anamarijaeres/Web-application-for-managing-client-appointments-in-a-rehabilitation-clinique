package opp.flow.model;

import java.lang.reflect.Field;

import javax.persistence.*;
import javax.validation.constraints.NotNull;



@Entity
public class Client implements AppUser{

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotNull
    private String username;
    
    private String password;
    
    private String firstname;
    
    private String lastname;

    public Client() {
    	
    }
   
	public Client(@NotNull String username, String password, String firstname, String lastname) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
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

    @Override
	public String toString() {
		return "Client [id=" + id + ", username=" + username + ", password=" + password + ", firstname=" + firstname
				+ ", lastname=" + lastname + "]";
	}
    
    public void replaceAttributes(Client update) throws IllegalArgumentException, IllegalAccessException {
    	Field[] fields = update.getClass().getDeclaredFields();
    	for(Field f:fields) {
    		if(f.get(update)!=null) {
    			f.set(this, f.get(update));
    		}
    	}
    }
}
