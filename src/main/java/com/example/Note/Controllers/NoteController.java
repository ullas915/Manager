package com.example.Note.Controllers;

import com.example.Note.Model.Note;
import com.example.Note.Service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping
    public List<Note> getAllNotes(Authentication auth) {
        return noteService.getAllNotes(auth.getName());
    }

    @PostMapping
    public Note createNote(@RequestBody Note note, Authentication auth) {
        return noteService.createNote(auth.getName(), note);
    }

    @PutMapping("/{id}")
    public Note updateNote(@PathVariable String id, @RequestBody Note note, Authentication auth) {
        return noteService.updateNote(auth.getName(), id, note);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable String id, Authentication auth) {
        noteService.deleteNote(auth.getName(), id);
        return ResponseEntity.ok("Note deleted");
    }




}
