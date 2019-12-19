package opp.flow.model;

public class AdminPost {
	private Long id;
	private String username;
	private String body;
	
	public AdminPost(Long id, DoctorCoach doctorCoach) {
		this.id=id;
		this.username=doctorCoach.getUsername();
		this.body=initAdminPostBody(doctorCoach);
	}

	private String initAdminPostBody(DoctorCoach doctorCoach) {
		return "Firstname: "+doctorCoach.getFirstname()+" Lastname: "+doctorCoach.getLastname()+
				" Email: "+doctorCoach.getEmail()+" Max number of clients: "+doctorCoach.getMaxNumClient();
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
