package opp.flow.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import opp.flow.model.*;
import opp.flow.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import opp.flow.UserRole;

@Service
public class DoctorCoachService {
	@Autowired
    private ClientRepository clientRepository;
	
	@Autowired
    private DoctorCoachRepository doctorCoachRepository;

	@Autowired
	private ClientService clientService;

	@Autowired
	private DoctorCoachRequestRepository doctorCoachRequestRepository;

	@Autowired
	private ClientCoachRepository clientCoachRepository;

	@Autowired
	private ClientDoctorRepository clientDoctorRepository;

	
	public boolean registerDoctorCoach(DoctorCoach registerDoctorCoach) {
    	Client client=clientRepository.findByusername(registerDoctorCoach.getUsername());
    	DoctorCoach doctorCoach=doctorCoachRepository.findByusername(registerDoctorCoach.getUsername());
    	if(client==null && doctorCoach==null) {
    		doctorCoachRepository.save(registerDoctorCoach);
    		return true;
    	}else {
    		return false;
    	}
    }
	
	public boolean saveDoctorCoachImage(String username, MultipartFile file) {
		try {
			DoctorCoach doctorCoach=doctorCoachRepository.findByusername(username);
			Byte[] byteObjects=new Byte[file.getBytes().length];
			
			int i=-1;
			for(byte b:file.getBytes()) {
				i++;
				byteObjects[i]=b;
			}
			
			doctorCoach.setImage(byteObjects);
			doctorCoachRepository.save(doctorCoach);
			return true;
		}catch(IOException e){
			e.printStackTrace();
			return false;
		}
	}
	
	public DoctorCoach loadDoctorCoach(String username, String password) {
		DoctorCoach doctorCoach=doctorCoachRepository.findByusername(username);
		if(doctorCoach==null) return null;
		if(!doctorCoach.getPassword().equals(password)) {
			return null;
		}
		return doctorCoach;
	}
	
	public UserRole getRoleOfDoctorCoach(String username) {
		DoctorCoach doctorCoach=doctorCoachRepository.findByusername(username);
		if(doctorCoach.getRole().toLowerCase().trim().equals("doctor")) {
			return UserRole.Doctor;
		}else {
			return UserRole.Coach;
		}
	}
	
	public AppUser getDoctorCoach(String username) {
		return doctorCoachRepository.findByusername(username);
	}

	public byte[] getImage(String username) {
		DoctorCoach user=doctorCoachRepository.findByusername(username);
		Byte[] image=user.getImage();
		byte[] byteImage=new byte[image.length];
		int i=-1;
		for(Byte b:image) {
			i++;
			byteImage[i]=b.byteValue();
		}
		return byteImage;
	}

	public void updateDoctorCoach(DoctorCoach doctorCoach) {
		doctorCoachRepository.save(doctorCoach);
	}

	public void deleteDoctorCoach(DoctorCoach doctorCoach) {
		doctorCoachRepository.delete(doctorCoach);
	}


	public boolean sendRequest(String usernameDocOrCoach, String usernameClient){
		int br=0;
		AppUser doctorCoach=getDoctorCoach(usernameDocOrCoach);
		List<DoctorCoachRequests> requests=doctorCoachRequestRepository.findAll();
		for(DoctorCoachRequests rq:requests){
			if(rq.getUsernameDoctorCoach().equals(usernameDocOrCoach)){
				++br;
			}

		}
		DoctorCoach doc=(DoctorCoach) doctorCoach;
		if(br<doc.getMaxNumClient()) {
			doctorCoachRequestRepository.save(new DoctorCoachRequests(usernameDocOrCoach, usernameClient));
			return true;
		}else{
			return false;
		}
	}

	public List<DocCoachPost> getRequestList(String username){
		List<DocCoachPost> lista=new ArrayList<>();
		Client cl;
		List<DoctorCoachRequests> listWithRequests=doctorCoachRequestRepository.findAll();
		for(DoctorCoachRequests rq: listWithRequests){
			if(rq.getUsernameDoctorCoach().equals(username)) {
				cl = (Client) clientService.getClient(rq.getClientUsername());
				lista.add(new DocCoachPost(rq.getId(), cl));
			}
		}
		return lista;
	}

	public boolean approveClient(String username, String usernameDocOrCoach) {
		DoctorCoach doctorCoach=(DoctorCoach)getDoctorCoach(usernameDocOrCoach);
		List<DoctorCoachRequests> listWithRequests=doctorCoachRequestRepository.findAll();
		boolean app=false;
		if(doctorCoach.getRole().equals("Doctor")){
			clientDoctorRepository.save(new ClientDoctor(username,usernameDocOrCoach));
			app=true;
		}
		else{
			clientCoachRepository.save(new ClientCoach(username,usernameDocOrCoach));
			app=true;
		}
		for(DoctorCoachRequests rq: listWithRequests) {
			if (rq.getUsernameDoctorCoach().equals(usernameDocOrCoach) && rq.getClientUsername().equals(username)) {
				doctorCoachRequestRepository.delete(rq);
			}
		}
		return app;
	}

	public void declineClient(String username, String usernameDocOrCoach) {
		List<DoctorCoachRequests> listWithRequests=doctorCoachRequestRepository.findAll();
		for(DoctorCoachRequests rq: listWithRequests) {
			if (rq.getUsernameDoctorCoach().equals(usernameDocOrCoach) && rq.getClientUsername().equals(username)) {
				doctorCoachRequestRepository.delete(rq);
			}
		}
	}

	public void updateDoctorCoachProfileData(DoctorCoach updateDoctorCoach) {
		DoctorCoach doctorCoach=doctorCoachRepository.findByusername(updateDoctorCoach.getUsername());
		try {
			doctorCoach.replaceAttributes(updateDoctorCoach);
		}catch(Exception e) {
			e.printStackTrace();
		}
		doctorCoachRepository.save(doctorCoach);
	}


	public List<DocCoachPost> getCooperations(String username) {
		List<DocCoachPost> list=new ArrayList<>();
		DoctorCoach dc=(DoctorCoach)getDoctorCoach(username);
		Client client=null;
		if(dc.getRole().equals("Doctor")) {
			List<ClientDoctor> listaClientDoctor = clientDoctorRepository.findAll();
			for (ClientDoctor cd : listaClientDoctor) {
				if (cd.getUsernamedoctor().equals(username)) {
					client=(Client)clientService.getClient(cd.getUsernameclient());
					list.add(new DocCoachPost(cd.getId(), client));
				}
			}
		}else{
			List<ClientCoach> listaClientCoach=clientCoachRepository.findAll();
			for(ClientCoach cc : listaClientCoach) {
				if (cc.getUsernamecoach().equals(username)) {
					client=(Client)clientService.getClient(cc.getUsernameclient());
					list.add(new DocCoachPost(cc.getId(), client));
				}
			}
		}
		return list;
	}
}
