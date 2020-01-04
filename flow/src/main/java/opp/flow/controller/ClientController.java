package opp.flow.controller;

import opp.flow.ErrorCode;
import opp.flow.ResponseMessage;
import opp.flow.model.Client;
import opp.flow.service.ClientService;
import opp.flow.service.DoctorCoachService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*; 
import opp.flow.model.*;

@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
public class ClientController {

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
    
    @GetMapping("/adminList")
    public List<AdminPost> getAdminListForApproval(){
    	return clientService.getAdminList();
    }

	@GetMapping("/doctorList")
	public List<AdminPost> getDoctors(){ return clientService.getDoctorsList(); }

	@GetMapping("/coachList")
	public List<AdminPost> getCoaches(){ return clientService.getCoachesList(); }




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
}
