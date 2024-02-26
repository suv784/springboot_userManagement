package com.org.UserManage.controller;

import java.util.List;

 
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.UserManage.Exception.NoSuchElementException;
import com.org.UserManage.Exception.NoSuchGenderFoundException;
import com.org.UserManage.Exception.SalaryNotFoundException;
import com.org.UserManage.Exception.UnexpectedTypeException;
import com.org.UserManage.dto.User;
import com.org.UserManage.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

	@Autowired 
	UserService service; 
	
	@PostMapping("/user/save")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String saveUser(@RequestBody @Valid User user) { 
		if (user == null || user.getName() == null || user.getName().isEmpty()||user.getEmail()==null||user.getEmail().isEmpty()||!isValidEmail(user.getEmail())||user.getPassword()==null||user.getPassword().isEmpty()||!isValidMobileNumber(user.getMobile())||user.getGender()==null||user.getGender().isEmpty()){
			
            throw new UnexpectedTypeException("please provide the correct details");
        }
		  
		return service.saveUser(user); 
	}
	 
	@PutMapping("/updateUser/{id}") 
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String updateById(@PathVariable("id") int userId,@RequestBody User user) { 
		
		return service.updateUserById(userId, user);
	}
	@GetMapping("/user/alluser") 
	public List<User>getAllUserDetails(){
		return service.fetchAllDetails();
	}
	
	@DeleteMapping("/user/deleteUser/{id}")  
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteUserById(@PathVariable("id") int userId) { 
        User user= service.isUserPresent(userId); 
        if (user!=null) {
            return  service.deleteUserById(userId);
        } else {
           throw new NoSuchElementException("no such Id Present in the datbase");
        }
    }

	@DeleteMapping("/user/delete/alluser")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String deleteAllUserDetails() {
		return service.deleteAllUserDetails();
	}  
	@GetMapping("/user/fetchByName/{name}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public List<User>fetchByName(@PathVariable("name")String name){  
		List<User> userdeatails=service.fetchByName(name); 
		if(userdeatails.isEmpty()) {
			throw new NoSuchElementException("no name found in the databse");
		}
		return userdeatails;
	} 
	@GetMapping("/user/fetchBySal/{sal}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Set<User>fetchBySalary(@PathVariable("sal")double salary){  
		Set<User> userSalaryDetails=service.fetchBySalary(salary);
		if(userSalaryDetails.isEmpty()) {
			throw new SalaryNotFoundException("no such salary is present in the database");
		}
		return userSalaryDetails; 
	}  
	@GetMapping("/user/gender/{gen}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Set<User>fetchUserByGender(@PathVariable("gen") String gender){
		Set<User>usergender=service.fetchByUserGender(gender);
		if(usergender.isEmpty()) { 
			throw new NoSuchGenderFoundException("that type of gender is not avialbe in the database");
		}
	
		return usergender;
	}
	
	@GetMapping("/user/desc/userSal") 
	@PreAuthorize("hasRole('ROLE_USER')")
	public List<User>getSalariesDesc(){ 
		return service.sortUserSalDesc(); 	
	}   
	@GetMapping("/user/asc/usersal") 
	@PreAuthorize("hasRole('ROLE_USER')")
	public List<User>getSalariesAsc(){
		return service.sortUserSalAsc();
	}
	
	@GetMapping("/user/query/{name}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	
	public String fetchsmallDetails(@PathVariable("name")String name,@RequestParam("phone")String mobile ){
		return"fetched name is-"+name+","+"phone number is:"+mobile;
	}  
	@GetMapping("/user/bigquery/{email}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String fetchbigDetails(@PathVariable("email")String email,@RequestParam("name")String name,@RequestParam("sal")double salary,@RequestParam("gen")String gender) {
		return "fetched name is-"+name+","
				+"fetch email is-"+email+","
				+"fetch salary is-"+salary+","
				+"fetch gender is-"+gender;
	}
	
	
	 public boolean isValidEmail(String email) {
	        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
	        Pattern pattern = Pattern.compile(regex);
	        return pattern.matcher(email).matches();
	    } 
	  public boolean isValidMobileNumber(long mobileNumber) {
		    String mobileNumberStr = String.valueOf(mobileNumber);
		    String regex = "^[789]\\d{9}$";
		    Pattern pattern = Pattern.compile(regex);
		    return pattern.matcher(mobileNumberStr).matches();
		}
}
	 
	

