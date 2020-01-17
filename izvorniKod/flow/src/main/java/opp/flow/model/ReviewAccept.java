package opp.flow.model;

public class ReviewAccept {
    String comment;
    int score;
    String usernameC;
    public ReviewAccept() {
    }

    public ReviewAccept(String comment, int score,String usernameC) {
        this.comment = comment;
        this.score = score;
        this.usernameC=usernameC;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUsernameC() {
        return usernameC;
    }
    public void setUsernameC(String usernameC) {
        this.usernameC = usernameC;
    }
}
