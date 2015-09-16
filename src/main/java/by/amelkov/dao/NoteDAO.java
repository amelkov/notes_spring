package by.amelkov.dao;

import by.amelkov.model.Note;
import by.amelkov.model.User;

import java.util.List;

public interface NoteDAO {
    void addNote(Note note);

    void editNote(Note note);

    void deleteNote(Note note);

    Note getNote(int id);

    Note getNoteAll(int id);

    List<Note> getUserNotes(User user);

    List<Note> getLastUserNotes(User user);

    List<Note> getAllNotes();
}
