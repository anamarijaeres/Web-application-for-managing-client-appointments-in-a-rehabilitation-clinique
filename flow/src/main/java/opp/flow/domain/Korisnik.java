package opp.flow.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Korisnik {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotNull
    private String username;
    
    private String lozinka;
    
    private String givenname;
    
    private String familyname;

    public Korisnik(@NotNull String username, String lozinka, String givenname, String familyname) {
		this.username = username;
		this.lozinka = lozinka;
		this.givenname = givenname;
		this.familyname = familyname;
	}
    
    public Korisnik() {
    	
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

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getGivenname() {
        return givenname;
    }

    public void setGivenname(String givenname) {
        this.givenname = givenname;
    }

    public String getFamilyname() {
        return familyname;
    }

    public void setFamilyname(String familyname) {
        this.familyname = familyname;
    }
    
    @Override
    public String toString() {
    	return "username: "+this.getUsername()+System.lineSeparator()+"password: "+this.getLozinka();
    }
}
