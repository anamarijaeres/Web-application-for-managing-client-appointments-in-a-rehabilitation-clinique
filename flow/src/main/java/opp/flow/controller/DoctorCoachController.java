package opp.flow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import opp.flow.ErrorCode;
import opp.flow.ResponseMessage;
import opp.flow.model.DoctorCoach;
import opp.flow.service.DoctorCoachService;

@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
public class DoctorCoachController {
	
	@Autowired
	private DoctorCoachService doctorCoachService;
	
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
	
	@PostMapping("/saveImage/{username}")
	public ResponseMessage saveDoctorCoachImage(@PathVariable String username, @RequestParam("imageFile") MultipartFile image) {
		ResponseMessage response=new ResponseMessage();
		boolean save=doctorCoachService.saveDoctorCoachImage(username, image);
		response.setUsername(username);
		response.setUserRole(doctorCoachService.getRoleOfDoctorCoach(username));
		System.out.println(username);
		System.out.println(image);
		
		if(save==true) {
			response.setError_code(ErrorCode.ERROR_CODE_0);
    		response.setMessage("Image saved successfuly");
		}else {
			response.setError_code(ErrorCode.ERROR_CODE_3);
    		response.setMessage("Unable to save image");
		}
		return response;
	}
}
