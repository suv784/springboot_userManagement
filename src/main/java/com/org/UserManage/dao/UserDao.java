package com.org.UserManage.dao;

import java.util.List;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.org.UserManage.dto.User;

@Component
public class UserDao {
	@Autowired  
	UserRepo repo; 
	
	public void saveUser(User user) {
		repo.save(user);
	}
	
	public boolean updateUser(int id, User user) {
		Optional<User> findById = repo.findById(id);  
		
		if(findById.isPresent()) {
			User user2 = findById.get();
			
			user2.setName(user.getName());
			user2.setEmail(user.getEmail());
			user2.setPassword(user.getPassword());
			user2.setMobile(user.getMobile());
			user2.setSalary(user.getSalary()); 
			
			repo.save(user2);
			return true;
		}
		return false;
	 }   
	public List<User>fetchByName(String name){
		return repo.findByName(name);		
	} 
	public Set<User>fetchBySalary(double sal){
		return repo.findBySalary(sal);
		
	} 
	public String deleteById(int id) { 
		
		repo.deleteById(id);
		return "user deleted";
		
	}  
	public List<User>getAllUserDetails(){
		return repo.findAll();
	} 
	public String deleteAllUserDetails() {
		repo.deleteAll();
		return"All User Details Deleted Succesfully";
	}
	
	public Set<User> findByUserGender(String gender){
		return repo.findByGender(gender);
	} 
	public User isUserExists(int id) { 
		return repo.findById(id).get();
		
	}
	
	

}
