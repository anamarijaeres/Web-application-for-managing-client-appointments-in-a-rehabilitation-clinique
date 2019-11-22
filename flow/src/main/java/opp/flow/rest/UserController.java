package opp.flow.rest;

import opp.flow.ErrorCode;
import opp.flow.ResponseMessage;
import opp.flow.domain.Korisnik;
import opp.flow.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:8080" })
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/korisnici")
    public ResponseMessage registerUser(@RequestBody Korisnik korisnik) {
    	Korisnik userDB=userService.loadUser(korisnik.getUsername(), korisnik.getLozinka());
    	ResponseMessage response=new ResponseMessage();
    	if(userDB!=null) {
    		response.setError_code(ErrorCode.ERROR_CODE2);
    		response.setMessage("Username taken");
    		response.setUsername(korisnik.getUsername());
    		return response;
    	}else {
    		response.setError_code(ErrorCode.ERROR_CODE0);
    		response.setMessage("");
    		response.setUsername(korisnik.getUsername());
    	}
    	userService.createUser(korisnik);
    	return response;
    }
    
    @PostMapping("/login")
    public ResponseMessage loginUser(@RequestParam("username") String username, @RequestParam("password") String password) {
    	Korisnik user=userService.loadUser(username, password);
    	ResponseMessage response=new ResponseMessage();
    	if(user==null) {
    		response.setError_code(ErrorCode.ERROR_CODE1);
    		response.setMessage("Wrong password or username");
    		response.setUsername(username);
    	}else {
    		response.setError_code(ErrorCode.ERROR_CODE0);
    		response.setMessage("");
    		response.setUsername(username);
    	}
    	return response;
    }
}
