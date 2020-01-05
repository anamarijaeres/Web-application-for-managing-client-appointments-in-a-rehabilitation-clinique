package opp.flow.model;

import javax.xml.stream.events.Comment;

public class ReviewPost {
    private Long id;
    private String username;
    private String body;

    public ReviewPost(Long id,  Review rev) {
        this.id=id;
        this.username=rev.getClientUsername();
        this.body=initReviewPostBody(rev);
    }

    private String initReviewPostBody(Review review) {
        return "Comment: " +review.getComment() +" Score "+review.getScore()+
                "Response: " + review.getResponse();
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
