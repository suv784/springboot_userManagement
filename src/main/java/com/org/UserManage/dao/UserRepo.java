package com.org.UserManage.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.UserManage.dto.User;

public interface UserRepo extends JpaRepository<User,Integer> {
	public List<User> findByName(String name);

	public Set<User> findBySalary(double salary) ; 
	
	public Set<User>findByGender(String gender);  
	
	//public boolean isUserExists(int id);
	
	
	
}
