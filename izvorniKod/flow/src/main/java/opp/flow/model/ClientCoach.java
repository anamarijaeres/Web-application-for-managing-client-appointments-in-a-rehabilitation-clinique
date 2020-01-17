package opp.flow.model;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Entity
public class ClientCoach {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotNull
    private String usernameclient;

    private String usernamecoach;

    public ClientCoach() {

    }

    public ClientCoach(@NotNull String usernameclient, String usernamecoach) {
        super();
        this.usernameclient = usernameclient;
        this.usernamecoach = usernamecoach;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getUsernameclient() { return usernameclient; }

    public void setUsernameclient(String usernameclient) { this.usernameclient = usernameclient; }

    public String getUsernamecoach() {
        return usernamecoach;
    }

    public void setUsernamecoach(String usernamecoach) {
        this.usernamecoach = usernamecoach;
    }
}
