package opp.flow.controller;

import opp.flow.ErrorCode;
import opp.flow.ResponseMessage;
import opp.flow.model.Client;
import opp.flow.service.ClientService;
import opp.flow.service.DoctorCoachService;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*; 
import opp.flow.model.*;

@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
public class ClientController {
	//ADMIN je client sa username: ADMIN i password: ADMIN

    @Autowired
    private ClientService clientService;
    
    @Autowired
    private DoctorCoachService doctorCoachService;

    
    @PostMapping("/registerClient")
    public ResponseMessage registerClient(@RequestBody Client clientRegister) {
    	ResponseMessage response=new ResponseMessage();
    	boolean save=clientService.registerClient(clientRegister);
    	response.setUsername(clientRegister.getUsername());
    	response.setUserRole(clientService.getRoleOfClient());
    	if(save==true) {
    		response.setError_code(ErrorCode.ERROR_CODE_0);
    		response.setMessage("Successful registration");
    	}else {
    		response.setError_code(ErrorCode.ERROR_CODE_2);
    		response.setMessage("Username is taken");
    	}
    	return response;
    }
<<<<<<< Updated upstream
	@GetMapping("/statistic/{username}")
	public List<StatisticProduct> getStatistic(@PathVariable String username){
		return clientService.getStatistic(username);
=======

    @GetMapping("/{username}/getDates")
	public List<LocalDate> getDates(@PathVariable("username") String username) {

    	return clientService.loadTrainingDates(username);
>>>>>>> Stashed changes
	}
    
    @GetMapping("/adminList")
    public List<AdminPost> getAdminListForApproval(){
    	return clientService.getAdminList();
    }

	@GetMapping("/doctorList")
	public List<AdminPost> getDoctors(){ return clientService.getDoctorsList(); }

	@GetMapping("/coachList")
	public List<AdminPost> getCoaches(){ return clientService.getCoachesList(); }

	@GetMapping("/cooperationDoctor/{username}")
	public AdminPost getCooperationDoctor(@PathVariable("username") String username){
    	return clientService.getCooperationDoctor(username);
	}
	@GetMapping("/cooperationCoach/{username}")
	public AdminPost getCooperationCoach(@PathVariable("username") String username){
		return clientService.getCooperationCoach(username);
	}
	@PostMapping("/breakCooperationDoctor/{username}")
	public ResponseMessage breakCooperationDoctor(@PathVariable("username") String username,@RequestBody String usernameClient){
		ResponseMessage response=new ResponseMessage();
		usernameClient=usernameClient.substring(0,usernameClient.length()-1);
		response.setUsername(username);
		response.setUserRole(clientService.getRoleOfClient());
    	doctorCoachService.breakCooperation(usernameClient,username);
		response.setMessage("You've broken cooperation!");
		return response;
	}
	@PostMapping("/breakCooperationCoach/{username}")
	public ResponseMessage breakCooperationCoach(@PathVariable("username") String username,@RequestBody String usernameClient){
		ResponseMessage response=new ResponseMessage();
		usernameClient=usernameClient.substring(0,usernameClient.length()-1);
		response.setUsername(username);
		response.setUserRole(clientService.getRoleOfClient());
		doctorCoachService.breakCooperation(usernameClient,username);
		response.setMessage("You've broken cooperation!");
		return response;
	}


	@PostMapping("/approve/{username}")
    public ResponseMessage updateApproval(@PathVariable("username") String username) {
    	ResponseMessage response=new ResponseMessage();
    	response.setError_code(ErrorCode.ERROR_CODE_5);
    	response.setUsername(username);
    	response.setMessage("Registration approved");
    	response.setUserRole(doctorCoachService.getRoleOfDoctorCoach(username));
    	
    	AppUser user=doctorCoachService.getDoctorCoach(username);
    	DoctorCoach doctorCoach=(DoctorCoach)user;
    	doctorCoach.setApprovedByAdmin(true);
    	doctorCoachService.updateDoctorCoach(doctorCoach);
    	return response;
    }
    
    @PostMapping("/delete/{username}")
    public ResponseMessage deleteRegistrationRequest(@PathVariable("username") String username) {
    	ResponseMessage response=new ResponseMessage();
    	response.setError_code(ErrorCode.ERROR_CODE_6);
    	response.setUsername(username);
    	response.setMessage("Registration denied");
    	response.setUserRole(doctorCoachService.getRoleOfDoctorCoach(username));
    	
    	AppUser user=doctorCoachService.getDoctorCoach(username);
    	DoctorCoach doctorCoach=(DoctorCoach)user;
    	doctorCoachService.deleteDoctorCoach(doctorCoach);
    	return response;
    }

    @PostMapping("/done/{username}")
	public ResponseMessage addTrainingInStatistics(@PathVariable("username") String username){
    	List<Training> trainings = doctorCoachService.loadTraining(username, LocalDate.now(),false);

    	for(Training t : trainings) {
    		t.setDone(true);
    		doctorCoachService.saveWorkout(t);

		}
    	doctorCoachService.saveTrainingStatistics(trainings);
		ResponseMessage response=new ResponseMessage();
		response.setError_code(ErrorCode.ERROR_CODE_0);
    	return response;
	}


}
