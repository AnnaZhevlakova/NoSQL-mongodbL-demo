package com.example.NoSQLMongodbL._demo.service;

import com.example.NoSQLMongodbL._demo.model.UserDTO;
import com.example.NoSQLMongodbL._demo.model.UserFilter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    public UserDTO getUser(String id) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    public List<UserDTO> getUsersByFilter(UserFilter filter) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }


    public String createUser(UserDTO user) {
        throw new UnsupportedOperationException("Method not implemented yet");

    }

    public boolean updateUser(UserDTO user) {
        throw new UnsupportedOperationException("Method not implemented yet");

    }

    public boolean deleteUser(String id) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }
}
