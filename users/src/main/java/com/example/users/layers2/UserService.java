package com.example.users.layers2;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.users.dtos.UserPatchDTO;
import com.example.users.dtos.UserPostDTO;

@Service
@Transactional
public class UserService {

	@Autowired
	UserRepository repository;
	
	public List<User> getAllUsers(){
		return this.repository.findAll();
	}
	
	public Optional<User> findUser(Long id){
		return this.repository.findById(id);
	}
	
	public User createUser(@Valid UserPostDTO data) {
//		Creating a new user entity
		User user = new User();
		
//		Setting the fields within our records with the values created from DTOs. As some DTO have a missing id, our entity will auto-generate a unique id for each record
		user.setFirstName(data.getFirstName());
		user.setLastName(data.getLastName());
		user.setJobTitle(data.getJobTitle());
		user.setEmail(data.getEmail());
		user.setImage(data.getImage());
		
		return this.repository.save(user);
	}
	
	public Optional<User> updateUser(Long id, UserPatchDTO data) {
//		Creating a new instance of entity based on id
		Optional<User> maybeUser = this.repository.findById(id);
		
//		If there is no existing user with the matching id, do nothing
		if(!maybeUser.isEmpty()) {
			return maybeUser;
		}
		
//		Will only execute if there is an existing entity with a matching id -> retrieve entity
		User user = maybeUser.get();
		
//		If field is not empty, update the existing record with its new value
		if(data.getJobTitle() != null) {
			user.setJobTitle(data.getJobTitle());
		}
		
		if(data.getImage() != null) {
			user.setImage(data.getImage());
		}
		
		return Optional.of(user);
		
	}
	
	public boolean deleteUser(Long id) {
		boolean existingUser = this.repository.existsById(id);
		
		if(!existingUser) {
			return false;
		}
		
		this.repository.deleteById(id);
		return true;
	}

	
	
	
}
