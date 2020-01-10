package opp.flow.controller;

import opp.flow.GetTrainingResponese;
import opp.flow.model.*;
import opp.flow.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import opp.flow.ErrorCode;
import opp.flow.ResponseMessage;
import opp.flow.service.DoctorCoachService;

import javax.xml.transform.sax.SAXResult;
import java.time.LocalDate;
import java.util.ArrayList;
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

	@GetMapping("/reviewList/{username}")
	public List<ReviewPost> getReviewList(@PathVariable String username){
		return doctorCoachService.getReviewList(username);
	}

	@GetMapping("/getExercises")
	public List<String> getExercises(){
		List<String> result = new ArrayList<>();
		List<Exercise> list = doctorCoachService.getExercises();
		for(Exercise l : list) {
			result.add(l.getName());
		}
		return result;
	}

	@GetMapping("/getWorkouts/{username}")
	public List<Training> getWorkouts(@PathVariable("username") String username){
		return doctorCoachService.loadTraining(username, LocalDate.now());
	}

	@GetMapping("/setTask/{clientUsername}")
	public GetTrainingResponese getTraining(@PathVariable("clientUsername") String clientUsername){

		List<Training> list = doctorCoachService.loadTraining(clientUsername, LocalDate.now());
		GetTrainingResponese responese = new GetTrainingResponese();
		if(list.size() != 0) {
			responese.setFlag(true);
			responese.setTraining(list);

		}
		else{
			responese.setFlag(false);
		}
		return responese;
	}
	@PostMapping("/addWorkout")
	public ResponseMessage addWorkout(@RequestBody Training training){
		training.setDate(LocalDate.now());
		doctorCoachService.saveWorkout(training);

		ResponseMessage responseMessage = new ResponseMessage();
		responseMessage.setError_code(ErrorCode.ERROR_CODE_0);
		return responseMessage;
	}


	@PostMapping("/reply/{username}")
	public ResponseMessage reviewDoctorCoach(@PathVariable("username") String usernameClient,@RequestBody ReplyAccept reply){
		ResponseMessage response=new ResponseMessage();
		doctorCoachService.postReply(usernameClient,reply);
		response.setMessage("Reply successful!");

		return response;
	}

	@PostMapping("/review/{username}")
	public ResponseMessage reviewDoctorCoach(@PathVariable("username") String username,@RequestBody ReviewAccept review){
		ResponseMessage response=new ResponseMessage();

		response.setUsername(username);
		response.setUserRole(clientService.getRoleOfClient());
		Review rev=new Review();
		rev.setClientUsername(review.getUsernameC());
		rev.setUsernameDoctorCoach(username);
		rev.setComment(review.getComment());
		rev.setScore(review.getScore());

		doctorCoachService.postReview(rev);
		response.setMessage("Review posted!");

		return response;
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
	@PostMapping("/breakCooperation/{username}")
	public ResponseMessage breakCooperation(@PathVariable("username") String username,@RequestBody String usernameDocOrCoach) {
		ResponseMessage response=new ResponseMessage();
		usernameDocOrCoach=usernameDocOrCoach.substring(0,usernameDocOrCoach.length()-1);
		response.setUsername(username);
		response.setUserRole(clientService.getRoleOfClient());

		doctorCoachService.breakCooperation(username,usernameDocOrCoach);
		response.setMessage("You've broken cooperation!");

		return response;
	}
	@PostMapping("/addExercise")
	public ResponseMessage addNewExercise(@RequestBody Exercise exercise) {
		ResponseMessage response=new ResponseMessage();
		boolean save=doctorCoachService.saveExercise(exercise);

		if(save==true) {
			response.setError_code(ErrorCode.ERROR_CODE_0);
			response.setMessage("Successful adding");
		}else {
			response.setError_code(ErrorCode.ERROR_CODE_6);
			response.setMessage("Adding failed");
		}
		return response;

	}
	@PostMapping("/addExercise/saveImage/{name}")
	public ResponseMessage saveExerciseImage (@PathVariable("name") String name, @RequestParam("imageFile") MultipartFile image) {
		ResponseMessage response=new ResponseMessage();
		boolean saveImage = doctorCoachService.saveExerciseImage(name, image);

		if(saveImage==true) {
			response.setError_code(ErrorCode.ERROR_CODE_0);
			response.setMessage("Successful adding");
		}else {
			response.setError_code(ErrorCode.ERROR_CODE_6);
			response.setMessage("Loading image failed");
		}
		return response;

	}




}
