package com.example.NoSQLMongodbL._demo.service;

import com.example.NoSQLMongodbL._demo.contracts.CreateUserRequest;
import com.example.NoSQLMongodbL._demo.model.UserDto;
import com.example.NoSQLMongodbL._demo.model.UserFilter;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    public UserDto getUser(String id) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    public List<UserDto> getUsersByFilter(UserFilter filter) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }


    public String createUser( UserDto user) {
        throw new UnsupportedOperationException("Method not implemented yet");

    }

    public boolean updateUser(UserDto user) {
        throw new UnsupportedOperationException("Method not implemented yet");

    }

    public boolean deleteUser(String id) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }
}
