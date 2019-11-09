package hr.fer.spring.projekt_opp_proba.model;

import javax.persistence.Entity;

import java.util.List;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name="Uloga")
public class Role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ulogaId")
	private Long roleId;
	
	@Column(name="Ime")
	private String roleName;
	
	@OneToMany(mappedBy="role")
	private List<User> users;
	
	public Role() {
		
	}

	public Role(Long roleId, String roleName) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
