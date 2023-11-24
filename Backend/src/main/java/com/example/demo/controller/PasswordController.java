package com.example.demo.controller;

import com.example.demo.model.Password;
import com.example.demo.model.User;
import com.example.demo.service.PasswordsService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/passwords")
public class PasswordController {

    @Autowired
    private PasswordsService passwordService;


    //http://localhost:8080/api/password/save
    // Create an endpoint to save a password
    @PostMapping("/save")
    public ResponseEntity<String> savePassword(@AuthenticationPrincipal User user,
                                               @RequestBody Password updatedPassword) {


        updatedPassword.setUserid(user.getId().toString());
        passwordService.save(updatedPassword);
        return ResponseEntity.ok("Password saved successfully.");
    }


//    http://localhost:8080/api/passwords/all
    @GetMapping("/all")
    public ResponseEntity<List<Password>> getAllPasswords(@AuthenticationPrincipal User user) {
        // Retrieve all passwords associated with the logged-in user
        List<Password> userPasswords = passwordService.passwordList(user.getId().toString());
        userPasswords.forEach(password -> password.setStringId(password.getId().toString()));
        return ResponseEntity.ok(userPasswords);
    }

    //Create end point to delete the password
//    http://localhost:8080/api/passwords/delete/id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePassword(@PathVariable ObjectId id){

        // Delete the password from the database
        passwordService.delete(id);

        return ResponseEntity.ok("Password deleted successfully.");
    }

//    http://localhost:8080/api/passwords/update/id
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatePassword(@PathVariable ObjectId id,@RequestBody Password updatedPassword){
        Password existingPassword = passwordService.findById(id);
        if (existingPassword == null) {
            return ResponseEntity.notFound().build();
        }


        // Update the password's details
        existingPassword.setWebsite(updatedPassword.getWebsite());
        existingPassword.setUsername(updatedPassword.getUsername());
        existingPassword.setEncryptedPassword(updatedPassword.getEncryptedPassword());

        // Save the updated password to the database
        passwordService.save(existingPassword);

        return ResponseEntity.ok("Password updated successfully.");
    }
}
