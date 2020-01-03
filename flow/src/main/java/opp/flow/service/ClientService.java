package opp.flow.service;

import opp.flow.UserRole;
import opp.flow.model.AdminPost;
import opp.flow.model.AppUser;
import opp.flow.model.Client;
import opp.flow.model.DoctorCoach;
import opp.flow.repository.ClientRepository;
import opp.flow.repository.DoctorCoachRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService{

    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private DoctorCoachRepository doctorCoachRepository;
    
    public boolean registerClient(Client registerClient) {
    	Client client=clientRepository.findByusername(registerClient.getUsername()); 
    	DoctorCoach doctorCoach=doctorCoachRepository.findByusername(registerClient.getUsername());
    	if(client==null && doctorCoach==null) {
    		clientRepository.save(registerClient);
    		return true;
    	}else {
    		return false;
    	}
    }

	public Client loadClient(String username, String password) {
		Client user=clientRepository.findByusername(username);
		if(user==null) return null;
		if(!user.getPassword().equals(password)) {
			return null;
		}
		return user;
	}
	
	public UserRole getRoleOfClient() {
		return UserRole.Client;
	}
	
	public AppUser getClient(String username) {
		return clientRepository.findByusername(username);
	}

	public List<AdminPost> getAdminList() {
		List<AdminPost> lista=new ArrayList<>();
		List<DoctorCoach>doctorCoachLista=doctorCoachRepository.findAll();
		for(DoctorCoach dc:doctorCoachLista) {
			if(dc.isApprovedByAdmin()==false) {
				lista.add(new AdminPost(dc.getId(), dc));
			}
		}
		return lista;
	}

	public void updateClientProfileData(Client updateClient) {
		Client client=clientRepository.findByusername(updateClient.getUsername());
		
		try {
			client.replaceAttributes(updateClient);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		clientRepository.save(client);
	}
}
