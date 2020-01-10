package opp.flow.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.prefs.Preferences;

import opp.flow.model.*;
import opp.flow.repository.*;
import org.apache.tomcat.jni.Local;
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

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private ExerciseRepository exerciseRepository;

	@Autowired
	private TrainingRepository trainingRepository;

	public boolean saveExercise(Exercise exercise){
		Exercise ex = exerciseRepository.findByname(exercise.getName());
		if (ex == null) {
			exerciseRepository.save(exercise);
			return true;
		}else {
			return false;
		}
	}

	public boolean saveExerciseImage(String name, MultipartFile file ){
		try {
			Exercise exercise = exerciseRepository.findByname(name);
			Byte[] byteObjects=new Byte[file.getBytes().length];

			int i=-1;
			for(byte b:file.getBytes()) {
				i++;
				byteObjects[i]=b;
			}

			exercise.setImage(byteObjects);
			exerciseRepository.save(exercise);
			return true;
		}catch(IOException e){
			e.printStackTrace();
			return false;
		}
	}

	public void addTraning(Training training){
		trainingRepository.save(training);
	}

	public List<Training> loadTraining(String username, LocalDate date){
		return trainingRepository.findByUsernameAndDate(username, date);
	}

	
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
		boolean saveDoctor=true;
		boolean saveCoach=true;
		AppUser doctorCoach=getDoctorCoach(usernameDocOrCoach);
		List<DoctorCoachRequests> requests=doctorCoachRequestRepository.findAll();
		List<ClientDoctor> clientDoctorList=clientDoctorRepository.findAll();
		List<ClientCoach> clientCoachList=clientCoachRepository.findAll();
		DoctorCoach doc=(DoctorCoach) doctorCoach;
		DoctorCoach d=null;
		for(DoctorCoachRequests rq:requests){
			if(rq.getUsernameDoctorCoach().equals(usernameDocOrCoach)) {
				++br;
			}
				if (rq.getClientUsername().equals(usernameClient)) {
					d=(DoctorCoach)getDoctorCoach(rq.getUsernameDoctorCoach());
					if (d.getRole().equals("Doctor")) {
						saveDoctor = false;
					} else {
						saveCoach = false;
					}
				}

		}

		if(doc.getRole().equals("Doctor")){
			for(ClientDoctor cd:clientDoctorList){
				if(cd.getUsernamedoctor().equals(usernameDocOrCoach)){
					++br;
				}
				if(cd.getUsernameclient().equals(usernameClient)){
					saveDoctor=false;
				}
			}
		}else{
			for(ClientCoach cc:clientCoachList){
				if(cc.getUsernamecoach().equals(usernameDocOrCoach)){
					++br;
				}
				if(cc.getUsernameclient().equals(usernameClient)){
					saveCoach=false;
				}
			}
		}
		System.out.println(saveCoach);
		System.out.println(saveDoctor);

		if(doc.getRole().equals("Doctor")) {
			if ((br < doc.getMaxNumClient()) && saveDoctor) {
				doctorCoachRequestRepository.save(new DoctorCoachRequests(usernameDocOrCoach, usernameClient));
			}
			return saveDoctor;
		}else {
			if ((br < doc.getMaxNumClient()) && saveCoach) {
				doctorCoachRequestRepository.save(new DoctorCoachRequests(usernameDocOrCoach, usernameClient));
			}
			return saveCoach;
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

	public void breakCooperation(String username, String usernameDocOrCoach) {
		DoctorCoach doctorCoach=(DoctorCoach)getDoctorCoach(usernameDocOrCoach);
		if(doctorCoach.getRole().equals("Doctor")){
			List<ClientDoctor> clientDoctorsList=clientDoctorRepository.findAll();
			for(ClientDoctor cd:clientDoctorsList){
				if(cd.getUsernamedoctor().equals(usernameDocOrCoach) && cd.getUsernameclient().equals(username)){
					clientDoctorRepository.delete(cd);
				}
			}
		}else{
			List<ClientCoach> clientCoachList=clientCoachRepository.findAll();
			for(ClientCoach cc:clientCoachList){
				if(cc.getUsernamecoach().equals(usernameDocOrCoach) && cc.getUsernameclient().equals(username)){
					clientCoachRepository.delete(cc);
				}
			}
		}
	}

	public List<ReviewPost> getReviewList(String username) {
		List<ReviewPost> lista = new ArrayList<>();
		List<Review> listofReviews = reviewRepository.findAll();
		for (Review rev : listofReviews) {
			if (rev.getUsernameDoctorCoach().equals(username))
				lista.add(new ReviewPost(rev.getId(),rev));

		}
		return lista;
	}

	public void postReview(Review review) {
		reviewRepository.save(review);
	}

	public void postReply(String usernameClient, ReplyAccept reply) {
		List<Review> reviews=reviewRepository.findAll();
		for(Review rev:reviews){
			if(rev.getUsernameDoctorCoach().equals(reply.getUsernameDocCoach())&& rev.getClientUsername().equals(usernameClient) &&Long.toString(rev.getId()).equals(reply.getId())){
				reviewRepository.delete(rev);
				reviewRepository.save(new Review(rev.getUsernameDoctorCoach(),rev.getClientUsername(),rev.getScore(),rev.getComment(),reply.getReply()));
			}
		}
	}

	public List<Exercise> getExercises() {
		return exerciseRepository.findAll();
	}

	public void saveWorkout(Training training) {
		trainingRepository.save(training);
	}


}
