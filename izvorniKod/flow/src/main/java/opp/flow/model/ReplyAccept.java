package opp.flow.model;

public class ReplyAccept {
    String reply;
    String id;
    String usernameDocCoach;

    public ReplyAccept(String reply,String id, String usernameDocCoach) {
        this.reply = reply;
        this.id=id;
        this.usernameDocCoach = usernameDocCoach;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getUsernameDocCoach() {
        return usernameDocCoach;
    }

    public void setUsernameDocCoach(String usernameDocCoach) {
        this.usernameDocCoach = usernameDocCoach;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
