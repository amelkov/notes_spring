package by.amelkov.service;

import by.amelkov.model.Note;
import by.amelkov.model.User;

import java.util.List;

public interface NoteService {
    void add(Note note);

    void edit(Note note);

    void delete(Note note);

    Note getNote(int id);

    Note getNoteAll(int id);

    List<Note> getUserNotes(User user);

    List<Note> getLastUserNotes(User user);

    List<Note> getAllNotes();
}