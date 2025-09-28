package com.example.NoSQLMongodbL._demo.service;

import com.example.NoSQLMongodbL._demo.entity.User;
import com.example.NoSQLMongodbL._demo.exceptions.UserException;
import com.example.NoSQLMongodbL._demo.model.UserDto;
import com.example.NoSQLMongodbL._demo.model.UserFilter;

import com.example.NoSQLMongodbL._demo.repository.UserRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private MongoTemplate mongoTemplate;

    public UserService(UserRepository userRepository, MongoTemplate mongoTemplate) {
        this.userRepository = userRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public UserDto getUser(String id) {

        var userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new UserException("User not found");
        }
        var userEntity = userOptional.get();
        return new UserDto(userEntity.getId(), userEntity.getName(), userEntity.getEmail(), userEntity.getAge());
    }

    public List<UserDto> getUsersByFilter(UserFilter filter) {
        Query query = new Query();
        List<Criteria> criteria = new ArrayList<>();

        if (filter.getName() != null && !filter.getName().isEmpty()) {
            criteria.add(Criteria.where("name").regex(filter.getName(), "i"));
        }
        if (filter.getAge() != null) {
            criteria.add(Criteria.where("age").gte(filter.getAge()));
        }
        if (!criteria.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteria));
        }
        var userList = mongoTemplate.find(query, User.class);
        var result = userList.stream()
                .map(user -> new UserDto(user.getId(), user.getName(), user.getEmail(), user.getAge()))
                .toList();
        return result;
    }


    public String createUser(UserDto user) {
        var userEntity = new User(null, user.getName(), user.getEmail(), user.getAge());
        userRepository.save(userEntity);
        return userEntity.getId();

    }

    public boolean updateUser(UserDto user) {
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if (!optionalUser.isPresent()) {
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
