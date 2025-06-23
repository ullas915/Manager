package com.example.Note.Service;

import com.example.Note.Model.Note;
import com.example.Note.Model.User;
import com.example.Note.Repository.NoteRepository;
import com.example.Note.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
public class NoteService {

    @Autowired private NoteRepository noteRepo;
    @Autowired
    private UserRepository userRepo;

    public List<Note> getAllNotes(String username) {
        User user = userRepo.findByUsername(username).orElseThrow();
        return noteRepo.findByUserId(user.getId());
    }

    public Note createNote(String username, Note note) {
        User user = userRepo.findByUsername(username).orElseThrow();
        note.setUserId(user.getId());
        return noteRepo.save(note);
    }

    public Note updateNote(String username, String id, Note updatedNote) {
        User user = userRepo.findByUsername(username).orElseThrow();
        Note existing = noteRepo.findById(id).orElseThrow();

        if (!existing.getUserId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not your note");
        }

        existing.setTitle(updatedNote.getTitle());
        existing.setContent(updatedNote.getContent());
        return noteRepo.save(existing);
    }

    public void deleteNote(String username, String id) {
        User user = userRepo.findByUsername(username).orElseThrow();
        Note note = noteRepo.findById(id).orElseThrow();

        if (!note.getUserId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not your note");
        }

        noteRepo.delete(note);
    }
}
