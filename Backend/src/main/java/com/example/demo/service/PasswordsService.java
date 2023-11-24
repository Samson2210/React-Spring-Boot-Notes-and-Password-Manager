package com.example.demo.service;

import com.example.demo.model.Password;
import com.example.demo.repository.PasswordRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PasswordsService {
    @Autowired
    private PasswordRepository passwordRepository;

    //Return all the password
    public List<Password> passwordList (String userId){
        return passwordRepository.findByUserid(userId);
    }

    //To find a particular password
    public Password findById(ObjectId passwordId) {
        return passwordRepository.findByid(passwordId);
    }

    //Store password in database
    public void save(Password password) {
        passwordRepository.save(password);
    }

    //delete from the database
    public void delete(ObjectId passwordId) {
        passwordRepository.deleteById(passwordId);
    }
}