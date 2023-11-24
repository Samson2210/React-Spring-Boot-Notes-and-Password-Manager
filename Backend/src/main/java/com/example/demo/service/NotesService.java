package com.example.demo.service;

import com.example.demo.model.Note;
import com.example.demo.repository.NoteRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NotesService {
    @Autowired
    private NoteRepository notesRepository;

    //Here you can pass  the user id to fetch all the notes of a particular user
    public List<Note> getAllNotes(String userID){
        return notesRepository.findByUserId(userID);
    }

    public Note singleNotes(ObjectId id){
        return notesRepository.findById(id);
    }

    public void save(Note note){
        notesRepository.save(note);
    }

    public void deleteNote(ObjectId id){
        notesRepository.deleteById(id);
    }
}
