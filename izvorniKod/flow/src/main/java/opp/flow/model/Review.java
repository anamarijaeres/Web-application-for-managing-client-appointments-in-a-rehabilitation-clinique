package opp.flow.model;

import javax.persistence.*;


@Entity
public class Review {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

   private String usernameDoctorCoach;

   private String clientUsername;

   private int score;

   private String comment;

   private String response;

    public Review(String usernameDoctorCoach,String clientUsername,int score,String comment,String response) {
        this.usernameDoctorCoach=usernameDoctorCoach;
        this.clientUsername=clientUsername;
        this.score=score;
        this.comment=comment;
        this.response=response;
    }

    public Review() {

    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getUsernameDoctorCoach() { return usernameDoctorCoach; }

    public void setUsernameDoctorCoach(String usernameDoctorCoach) { this.usernameDoctorCoach = usernameDoctorCoach; }

    public String getClientUsername() { return clientUsername; }

    public void setClientUsername(String clientUsername) { this.clientUsername = clientUsername; }

    public int getScore() { return score; }

    public void setScore(int score) { this.score = score; }

    public String getComment() { return comment; }

    public void setComment(String comment) { this.comment = comment; }

    public String getResponse() { return response; }

    public void setResponse(String response) { this.response = response; }
}
