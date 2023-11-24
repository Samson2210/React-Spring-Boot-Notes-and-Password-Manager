package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection="notes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    @Id
    private ObjectId id;
    @Field("id")
    private String stringId;
    private String  userId; //Reference user
    private String title;
    private String description;
}
