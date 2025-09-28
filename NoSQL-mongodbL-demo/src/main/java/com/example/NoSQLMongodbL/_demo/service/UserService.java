package com.example.NoSQLMongodbL._demo.service;

import com.example.NoSQLMongodbL._demo.entity.User;
import com.example.NoSQLMongodbL._demo.exceptions.UserException;
import com.example.NoSQLMongodbL._demo.model.UserDto;
import com.example.NoSQLMongodbL._demo.model.UserFilter;

import com.example.NoSQLMongodbL._demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserDto getUser(String id) {

        var userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()){
            throw new UserException("User not found");
        }
        var userEntity = userOptional.get();
        return new UserDto(userEntity.getId(),userEntity.getName(),userEntity.getEmail(), userEntity.getAge());
    }

    public List<UserDto> getUsersByFilter(UserFilter filter) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }


    public String createUser( UserDto user) {
        var userEntity = new User(null,user.getName(),user.getEmail(),user.getAge());
        userRepository.save(userEntity);
        return userEntity.getId();

    }

    public boolean updateUser(UserDto user) {
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if (!optionalUser.isPresent()){
           throw new UserException("User not found");
        }
        User existingUser = optionalUser.get();

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setAge(user.getAge());

        userRepository.save(existingUser);
        return true;
    }


    public boolean deleteUser(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            throw new UserException("User not found");
        }
    }
}
