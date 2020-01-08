package opp.flow.service;

import opp.flow.UserRole;
import opp.flow.model.*;
import opp.flow.repository.ClientCoachRepository;
import opp.flow.repository.ClientDoctorRepository;
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

	@Autowired
	private ClientDoctorRepository clientDoctorRepository;

	@Autowired
	private ClientCoachRepository clientCoachRepository;

	@Autowired
	private DoctorCoachService doctorCoachService;

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
	
	public UserRole getRoleOfClient() { return UserRole.Client; }
	
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

	public List<AdminPost> getDoctorsList(){
    	int brCoop=0;
    	List<AdminPost> lista=new ArrayList<>();
    	List<DoctorCoach>doctorCoachLista=doctorCoachRepository.findAll();
    	List<ClientDoctor> clientDoctorsList=clientDoctorRepository.findAll();
    	for(DoctorCoach dc:doctorCoachLista){
			if(dc.isApprovedByAdmin()==true) {
				if (dc.getRole().equals("Doctor")) {
    				for(ClientDoctor cd:clientDoctorsList){
    					if(cd.getUsernamedoctor().equals(dc.getUsername()))
    						++brCoop;
					}
    				if(brCoop<dc.getMaxNumClient())
						lista.add(new AdminPost(dc.getId(), dc));
				}
			}
		}
    	return lista;
	}
	public List<AdminPost> getCoachesList() {
    	int brCoop=0;
		List<AdminPost> lista = new ArrayList<>();
		List<DoctorCoach> doctorCoachLista = doctorCoachRepository.findAll();
		List<ClientCoach> clientCoachesList=clientCoachRepository.findAll();
		for (DoctorCoach dc : doctorCoachLista) {
			if (dc.isApprovedByAdmin() == true) {
				if (dc.getRole().equals("Coach")) {
					for(ClientCoach cc:clientCoachesList){
						if(cc.getUsernamecoach().equals(dc.getUsername()))
							++brCoop;
					}
					if(brCoop<dc.getMaxNumClient())
					lista.add(new AdminPost(dc.getId(), dc));
				}
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

    public AdminPost getCooperationDoctor(String username) {
		List<ClientDoctor> listaClientDoctor=clientDoctorRepository.findAll();
		AdminPost post=null;
		DoctorCoach doctor=null;
		for(ClientDoctor cd: listaClientDoctor){
			if(cd.getUsernameclient().equals(username)){
				doctor=(DoctorCoach)doctorCoachService.getDoctorCoach(cd.getUsernamedoctor());
				post=new AdminPost(cd.getId(),doctor);
			}
		}
		return post;
    }

	public AdminPost getCooperationCoach(String username) {
		List<ClientCoach> listaClientCoach=clientCoachRepository.findAll();
		AdminPost post=null;
		DoctorCoach coach=null;
		for(ClientCoach cc: listaClientCoach){
			if(cc.getUsernameclient().equals(username)){
				coach=(DoctorCoach)doctorCoachService.getDoctorCoach(cc.getUsernamecoach());
				post=new AdminPost(cc.getId(),coach);
			}
		}
		return post;
	}
}
