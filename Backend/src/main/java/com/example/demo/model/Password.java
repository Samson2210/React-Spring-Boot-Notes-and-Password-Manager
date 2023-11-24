package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "passwords")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Password {
    @Id
    private ObjectId id;
    @Field("id")
    private String stringId;
    private String userid;
    private String website;
    private String username;
    private String encryptedPassword;
}
