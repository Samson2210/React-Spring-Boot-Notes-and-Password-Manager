package com.example.demo.controller;

import com.example.demo.model.Note;
import com.example.demo.model.User;
import com.example.demo.service.NotesService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")

public class NotesController {
    @Autowired
    private NotesService notesService;

//    http://localhost:8080/api/notes/add/
    @PostMapping("/add")
    public  ResponseEntity<String> saveNote( @AuthenticationPrincipal User user, @RequestBody Note noteRequest){
        Note note = new Note();
        note.setUserId(String.valueOf(user.getId()));
        note.setTitle(noteRequest.getTitle());
        note.setDescription(noteRequest.getDescription());
        notesService.save(note);
        return ResponseEntity.ok("Note saved Successfully");
    }

//    http://localhost:8080/api/notes/update/{notedId}
    @PutMapping("/update/{id}")
    public ResponseEntity<String> getSingleNotes(@PathVariable ObjectId id, @RequestBody Note updateNote){
        Note existingNote = notesService.singleNotes(id);

        if(existingNote == null){
            return ResponseEntity.notFound().build();
        }

        //Update the note
        existingNote.setTitle(updateNote.getTitle());
        existingNote.setDescription(updateNote.getDescription());

        notesService.save(existingNote);
        return ResponseEntity.ok("Note updated Successfully");
    }


//    http://localhost:8080/api/notes/all
    @GetMapping("/all")
    public ResponseEntity<List<Note>> getAllNotes(@AuthenticationPrincipal User user){
        List<Note> userNotes = notesService.getAllNotes(user.getId().toString());
        // Convert ObjectId to string for each Note
        userNotes.forEach(note -> note.setStringId(note.getId().toString()));
        return ResponseEntity.ok(userNotes);
    }

//    http://localhost:8080/api/notes/all
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteNote(@PathVariable ObjectId id){
        notesService.deleteNote(id);
        return ResponseEntity.ok("Note deleted successfully");
    }

    @GetMapping
    public ResponseEntity<String> test(@AuthenticationPrincipal User user){
        return ResponseEntity.ok("Success"+ user.getId());
    }
}
