package opp.flow.model;

public class DocCoachPost {
    private Long id;
    private String username;
    private String body;

    public DocCoachPost(Long id, Client client) {
        this.id=id;
        this.username=client.getUsername();
        this.body=initAdminPostBody(client);
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getBody() { return body; }

    public void setBody(String body) { this.body = body; }

    private String initAdminPostBody(Client client) {
        return "Firstname: "+client.getFirstname()+" Lastname: "+client.getLastname();
    }
}
