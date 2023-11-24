package com.example.demo.repository;

import com.example.demo.model.Note;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {
    List<Note> findByUserId(String userId);
    Note findById(ObjectId id);
    void deleteById(ObjectId id);
}
