package com.example.NoSQLMongodbL._demo.controller;

import com.example.NoSQLMongodbL._demo.contracts.CreateUserRequest;
import com.example.NoSQLMongodbL._demo.model.UserDto;
import com.example.NoSQLMongodbL._demo.model.UserFilter;
import com.example.NoSQLMongodbL._demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping("{id}")
    public ResponseEntity<?> getUser(@PathVariable String id) {
        var result = userService.getUser(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("byFilter")
    public ResponseEntity<?> getUsers(@RequestBody UserFilter filter){
        var result = userService.getUsersByFilter(filter);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        var userDto = new UserDto(null,createUserRequest.getName(),createUserRequest.getEmail(),createUserRequest.getAge());
        var result = userService.createUser(userDto);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UserDto user) {
        var result = userService.updateUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        var result = userService.deleteUser(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
