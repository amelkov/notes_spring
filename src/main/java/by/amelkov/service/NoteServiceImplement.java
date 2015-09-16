package by.amelkov.service;

import by.amelkov.dao.NoteDAO;
import by.amelkov.model.Note;
import by.amelkov.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NoteServiceImplement implements NoteService {

    @Autowired
    private NoteDAO noteDAO;

    @Override
    public void add(Note note) {
        noteDAO.addNote(note);
    }

    @Override
    public void edit(Note note) {
        noteDAO.editNote(note);
    }

    @Override
    public void delete(Note note) {
        noteDAO.deleteNote(note);
    }

    @Override
    public Note getNote(int id) {
        return noteDAO.getNote(id);
    }

    @Override
    public Note getNoteAll(int id) {
        return noteDAO.getNoteAll(id);
    }

    @Override
    public List<Note> getUserNotes(User user) {
        return noteDAO.getUserNotes(user);
    }

    @Override
    public List<Note> getLastUserNotes(User user) {
        return noteDAO.getLastUserNotes(user);
    }

    @Override
    public List<Note> getAllNotes() {
        return noteDAO.getAllNotes();
    }
}