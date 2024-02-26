package com.org.springbootproject.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.org.springbootproject.dao.UserDao;
import com.org.springbootproject.dto.User;

@Service
public class UserService { 
	
	@Autowired
	UserDao dao;
	
	public String saveUser(User user) {
		dao.saveUser(user);
		return "data saved successfully";
	}
	
	public String updateUserById(int id, User user) { 
		
		boolean isPresent = dao.updateUser(id, user);
				if(isPresent) {
					return "data updated successfully";
				}
		return "Id Not Found";
	}
	
	public List<User>fetchByName(String name){
		return dao.fetchByName(name);		
	} 
	public Set<User>fetchBySalary(double sal){
		return dao.fetchBySalary(sal);
	} 
	public String deleteUserById(int id) {
		return dao.deleteById(id);
	}  
	public List<User>sortUserSalDesc(){ 
		List<User>userdetails=dao.getAllUserDetails(); 
		Collections.sort(userdetails,new Comparator<User>() {

			@Override 
			public int compare(User o1, User o2) {
				
				return Double.compare(o2.getSalary(),o1.getSalary());
			}
		});
		return userdetails;
		
} 
public List<User>fetchAllDetails(){ 
	
	return dao.getAllUserDetails();
}

public String deleteAllUserDetails() {
	return dao.deleteAllUserDetails();
}  
public List<User>sortUserSalAsc(){
	List<User>userdetailsasc=dao.getAllUserDetails();
	Collections.sort(userdetailsasc,new Comparator<User>() {
		@Override
		public int compare(User o1, User o2) {
		return	Double.compare(o1.getSalary(), o2.getSalary()); 
			
		}
	});
	return userdetailsasc;
} 
public Set<User>fetchByUserGender(String gender){
	return dao.findByUserGender(gender);
}  

public User isUserPresent(int id) {
	return dao.isUserExists(id);
	
}

}
