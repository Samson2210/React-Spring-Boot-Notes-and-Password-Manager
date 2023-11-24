package com.example.demo.repository;

import com.example.demo.model.Password;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PasswordRepository extends MongoRepository<Password, ObjectId> {
    List<Password> findByUserid(String userId);

    Password findByid(ObjectId id);

    void deleteById(ObjectId id);

}