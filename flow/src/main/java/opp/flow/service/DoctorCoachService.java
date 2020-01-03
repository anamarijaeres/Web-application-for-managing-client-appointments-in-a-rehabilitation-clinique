package opp.flow.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import opp.flow.UserRole;
import opp.flow.model.AppUser;
import opp.flow.model.Client;
import opp.flow.model.DoctorCoach;
import opp.flow.repository.ClientRepository;
import opp.flow.repository.DoctorCoachRepository;

@Service
public class DoctorCoachService {
	@Autowired
    private ClientRepository clientRepository;
	
	@Autowired
    private DoctorCoachRepository doctorCoachRepository;
	
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

	public void updateDoctorCoachProfileData(DoctorCoach updateDoctorCoach) {
		DoctorCoach doctorCoach=doctorCoachRepository.findByusername(updateDoctorCoach.getUsername());
		try {
			doctorCoach.replaceAttributes(updateDoctorCoach);
		}catch(Exception e) {
			e.printStackTrace();
		}
		doctorCoachRepository.save(doctorCoach);
	}
	
}
