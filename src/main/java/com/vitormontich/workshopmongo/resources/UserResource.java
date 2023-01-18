package com.vitormontich.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitormontich.workshopmongo.domain.User;
import com.vitormontich.workshopmongo.dto.UserDTO;
import com.vitormontich.workshopmongo.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = userService.findAll();
		List<UserDTO> listUserDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listUserDTO);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<UserDTO> findById( @PathVariable String id ) {
		User user = userService.findById(id);
		return ResponseEntity.ok().body(new UserDTO(user));
	}

}
