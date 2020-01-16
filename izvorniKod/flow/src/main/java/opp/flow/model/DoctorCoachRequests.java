package opp.flow.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
@Entity
public class DoctorCoachRequests {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    String usernameDoctorCoach;
    @Column( nullable = false)
    @NotNull
    String clientUsername;

    public DoctorCoachRequests(){};

    public DoctorCoachRequests(String usernameDoctorCoach,String clientUsername) {
        this.usernameDoctorCoach=usernameDoctorCoach;
        this.clientUsername=clientUsername;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsernameDoctorCoach() {
        return usernameDoctorCoach;
    }

    public void setUsernameDoctorCoach(String usernameDoctorCoach) {
        this.usernameDoctorCoach = usernameDoctorCoach;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }
}
