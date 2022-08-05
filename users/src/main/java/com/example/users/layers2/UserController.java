package com.example.users.layers2;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.users.dtos.UserPatchDTO;
import com.example.users.dtos.UserPostDTO;

@RestController
@RequestMapping(value = "/users")
@CrossOrigin(origins = "http://localhost:4200")


public class UserController {
	
	//	dependency injection of service provider into rest controller
	@Autowired
	UserService service;


	@GetMapping
	public List<User> getAllUsers() {
		return this.service.getAllUsers();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findUser(@PathVariable Long id){
//		Creates a new instance of the entity based on the id
		Optional<User> maybeUser = this.service.findUser(id);
		
//		If there is no existing user entity with a matching id, return a response entity with a null body and http status code of NOT_FOUND
		if(maybeUser.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
//		else return the corresponding user and information with a matching id 
		return new ResponseEntity<>(maybeUser.get(), HttpStatus.OK);
		
	}
	
	
	@PostMapping
	public ResponseEntity<User> createUser(@Valid @RequestBody UserPostDTO data ){
//		Create a new instance of our user entity and assign it the values of the service provider create method
		User user = this.service.createUser(data);
		
//		Return a new response user entity
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserPatchDTO data){
//		Creates a new instance of the entity based on the id and existing data
		Optional<User> maybeUser = this.service.updateUser(id, data);
		
//		If there is no existing entity with a matching id, return a response entity with a null body and httpstatus code of NOT_FOUND
		if(!maybeUser.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(maybeUser.get(), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id){
		boolean isFoundUser = this.service.deleteUser(id);
		
		if(!isFoundUser) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		
	}
	
}
