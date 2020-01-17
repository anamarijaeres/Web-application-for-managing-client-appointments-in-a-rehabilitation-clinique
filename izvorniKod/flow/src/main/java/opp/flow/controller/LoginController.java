package opp.flow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import opp.flow.ErrorCode;
import opp.flow.ResponseMessage;
import opp.flow.model.AppUser;
import opp.flow.model.Client;
import opp.flow.model.DoctorCoach;
import opp.flow.service.ClientService;
import opp.flow.service.DoctorCoachService;

@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
public class LoginController {
	@Autowired
	private DoctorCoachService doctorCoachService;

    @Autowired
    private ClientService clientService;
	
    @PostMapping("/login")
    public ResponseMessage loginUser(@RequestParam("username") String username, @RequestParam("password") String password) {
    	ResponseMessage response=new ResponseMessage();
    	response.setUsername(username);
    	DoctorCoach loginDoctorCoach=doctorCoachService.loadDoctorCoach(username, password);
    	Client loginClient=clientService.loadClient(username, password);
    	
    	if(loginClient==null && loginDoctorCoach==null) {
    		response.setError_code(ErrorCode.ERROR_CODE_1);
    		response.setMessage("Wrong username or password");
    	}else if(loginDoctorCoach!=null) {
    		response.setUserRole(doctorCoachService.getRoleOfDoctorCoach(username));
    		if(loginDoctorCoach.isApprovedByAdmin()==true) {
    			response.setError_code(ErrorCode.ERROR_CODE_0);
    			response.setMessage("Successful login");
    		}else {
    			response.setError_code(ErrorCode.ERROR_CODE_4);
    			response.setMessage("Admin needs to approve your registration");
    		}
    	}else if(loginClient!=null) {
    		response.setUserRole(clientService.getRoleOfClient());
    		response.setError_code(ErrorCode.ERROR_CODE_0);
			response.setMessage("Successful login");
    	}
    	
    	return response;
    }
    
    @PostMapping("/{username}/Client/editProfile")
    public ResponseMessage updateClientProfileData(@PathVariable("username") String username, @RequestBody Client updateClient) {
    	ResponseMessage response=new ResponseMessage();
    	response.setUsername(username);
    	response.setUserRole(clientService.getRoleOfClient());
    	clientService.updateClientProfileData(updateClient);
    	response.setError_code(ErrorCode.ERROR_CODE_0);
    	response.setMessage("Update successful");
    	return response;
    }
    
    @PostMapping("/{username}/DoctorCoach/editProfile")
    public ResponseMessage updateDoctorCoachProfileData(@PathVariable("username") String username, @RequestBody DoctorCoach updateDoctorCoach) {
    	ResponseMessage response=new ResponseMessage();
    	response.setUsername(username);
    	response.setUserRole(clientService.getRoleOfClient());
    	doctorCoachService.updateDoctorCoachProfileData(updateDoctorCoach);
    	response.setError_code(ErrorCode.ERROR_CODE_0);
    	response.setMessage("Update successful");
    	return response;
    }
    
    @GetMapping("/{username}")
    public AppUser getUser(@PathVariable("username") String username) {
    	AppUser response=clientService.getClient(username);
    	if(response==null)
    		response=doctorCoachService.getDoctorCoach(username);
    	return response;
    } 
    
    @GetMapping("/img/{username}")
    public ResponseEntity<byte[]> getImage(@PathVariable("username") String username){
    	byte[] image=doctorCoachService.getImage(username);
    	return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image);
    }
}
