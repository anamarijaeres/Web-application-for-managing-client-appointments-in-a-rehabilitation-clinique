package opp.flow.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Entity
public class ClientDoctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotNull
    private String usernameclient;

    private String usernamedoctor;

    public ClientDoctor() {

    }

    public ClientDoctor(@NotNull String usernameclient, String usernamedoctor) {
        super();
        this.usernameclient = usernameclient;
        this.usernamedoctor = usernamedoctor;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getUsernameclient() { return usernameclient; }

    public void setUsernameclient(String usernameclient) { this.usernameclient = usernameclient; }

    public String getUsernamedoctor() { return usernamedoctor; }

    public void setUsernamedoctor(String usernamedoctor) { this.usernamedoctor = usernamedoctor; }
}
