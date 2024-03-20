package com.hubgamers.api.controller;

import com.hubgamers.api.model.User;
import com.hubgamers.api.model.dto.UserDTO;
import com.hubgamers.api.response.ResponseJson;
import com.hubgamers.api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/columns")
    public ResponseJson<List<String>> getColumns() {
        return new ResponseJson<>(userService.getColumns(), HttpStatus.OK.value());
    }

    @GetMapping("/all")
    public ResponseJson<List<User>> getAllUsers() {
        return new ResponseJson<>(userService.getAllUsers(), HttpStatus.OK.value());
    }

    @GetMapping("/username/{username}")
    public ResponseJson<User> getUserByUsername(@PathVariable String username) {
        return new ResponseJson<>(userService.getUserByUsername(username), HttpStatus.OK.value());
    }

    @PutMapping("/update")
    public ResponseJson<User> updateUser(@RequestBody UserDTO userDTO) {
        return new ResponseJson<>(userService.updateUser(userDTO), HttpStatus.OK.value());
    }
}
