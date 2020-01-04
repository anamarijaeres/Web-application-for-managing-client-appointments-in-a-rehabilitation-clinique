package opp.flow.controller;

import opp.flow.model.*;
import opp.flow.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import opp.flow.ErrorCode;
import opp.flow.ResponseMessage;
import opp.flow.service.DoctorCoachService;

import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
public class DoctorCoachController {
	
	@Autowired
	private DoctorCoachService doctorCoachService;

	@Autowired
	private ClientService clientService;

	@GetMapping("/cooperations/{username}")
	public List<DocCoachPost> getCooperations(@PathVariable("username") String username){
		 return doctorCoachService.getCooperations(username);
	}
	@GetMapping("/requests/{username}")
	public List<DocCoachPost> getAdminListForApproval(@PathVariable String username){
		return doctorCoachService.getRequestList(username);
	}
	
	@PostMapping("/registerDoctorCoach")
	public ResponseMessage registerDoctorCoach(@RequestBody DoctorCoach registerDoctorCoach) {
		ResponseMessage response=new ResponseMessage();
		boolean save=doctorCoachService.registerDoctorCoach(registerDoctorCoach);
		response.setUsername(registerDoctorCoach.getUsername());
		response.setUserRole(doctorCoachService.getRoleOfDoctorCoach(registerDoctorCoach.getUsername()));
		
		if(save==true) {
    		response.setError_code(ErrorCode.ERROR_CODE_0);
    		response.setMessage("Successful registration");
    	}else {
    		response.setError_code(ErrorCode.ERROR_CODE_2);
    		response.setMessage("Username is taken");
    	}
		return response;
	}
	@PostMapping("/sendRequest/{username}")
	public ResponseMessage sendRequestToDoctorCoach(@PathVariable String username, @RequestBody UserRequest req){
		ResponseMessage response=new ResponseMessage();
		boolean send=doctorCoachService.sendRequest(username,req.getUsername());
		response.setUsername(username);
		response.setUserRole(doctorCoachService.getRoleOfDoctorCoach(username));
		if(send==true){
			response.setError_code((ErrorCode.ERROR_CODE_0));
			response.setMessage("Request sent successfully!");
		}else{
			response.setError_code((ErrorCode.ERROR_CODE_7));
			response.setMessage("Request denied!");
		}
		return response;
	}
	@PostMapping("/saveImage/{username}")
	public ResponseMessage saveDoctorCoachImage(@PathVariable String username, @RequestParam("imageFile") MultipartFile image) {
		ResponseMessage response=new ResponseMessage();
		boolean save=doctorCoachService.saveDoctorCoachImage(username, image);
		response.setUsername(username);
		response.setUserRole(doctorCoachService.getRoleOfDoctorCoach(username));
		
		if(save==true) {
			response.setError_code(ErrorCode.ERROR_CODE_0);
    		response.setMessage("Image saved successfuly");
		}else {
			response.setError_code(ErrorCode.ERROR_CODE_3);
    		response.setMessage("Unable to save image");
		}
		return response;
	}
	@PostMapping("/approveRequest/{username}")
	public ResponseMessage approveRequest(@PathVariable("username") String username,@RequestBody String usernameDocOrCoach) {
		ResponseMessage response=new ResponseMessage();
		usernameDocOrCoach=usernameDocOrCoach.substring(0,usernameDocOrCoach.length()-1);
		response.setUsername(username);
		response.setUserRole(clientService.getRoleOfClient());

		boolean approve=doctorCoachService.approveClient(username,usernameDocOrCoach);
		if(approve){
			response.setError_code(ErrorCode.ERROR_CODE_8);
			response.setMessage("You've approved client "+username);
		}else{
			response.setError_code(ErrorCode.ERROR_CODE_7);
			response.setMessage("Request denied!");
		}
		return response;
	}
	@PostMapping("/deleteRequest/{username}")
	public ResponseMessage deleteRequest(@PathVariable("username") String username,@RequestBody String usernameDocOrCoach) {
		ResponseMessage response=new ResponseMessage();
		usernameDocOrCoach=usernameDocOrCoach.substring(0,usernameDocOrCoach.length()-1);
		response.setUsername(username);
		response.setUserRole(clientService.getRoleOfClient());

		doctorCoachService.declineClient(username,usernameDocOrCoach);
		response.setError_code(ErrorCode.ERROR_CODE_7);
		response.setMessage("Request denied!");

		return response;
	}



}
