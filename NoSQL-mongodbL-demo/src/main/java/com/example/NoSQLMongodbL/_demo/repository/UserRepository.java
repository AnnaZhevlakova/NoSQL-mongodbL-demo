package com.example.NoSQLMongodbL._demo.repository;

import com.example.NoSQLMongodbL._demo.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {


}
