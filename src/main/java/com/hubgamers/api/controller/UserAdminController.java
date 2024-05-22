package com.hubgamers.api.controller;

import com.hubgamers.api.model.User;
import com.hubgamers.api.model.dto.UserDTO;
import com.hubgamers.api.response.ResponseJson;
import com.hubgamers.api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin/user")
public class UserAdminController {
	
	private final UserService userService;
	
	public UserAdminController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/columns")
	public ResponseJson<List<String>> getColumns() {
		return new ResponseJson<>(userService.getAdminColumns(), HttpStatus.OK.value());
	}
	
	@PostMapping("/create")
	public ResponseJson<User> createUser(@RequestBody UserDTO userDTO) {
		return new ResponseJson<>(userService.createUser(userDTO), HttpStatus.CREATED.value());
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteUser(String id) {
		userService.deleteUser(id);
	}
}
