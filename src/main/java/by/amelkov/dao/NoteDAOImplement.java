package by.amelkov.dao;

import by.amelkov.model.Note;
import by.amelkov.model.User;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoteDAOImplement implements NoteDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void addNote(Note note) {
        sessionFactory.getCurrentSession().save(note);
    }

    public void editNote(Note note) {
        sessionFactory.getCurrentSession().update(note);
    }

    public void deleteNote(Note note) {
        sessionFactory.getCurrentSession().delete(note);
    }

    public Note getNote(int id) {
        return (Note) sessionFactory.getCurrentSession().get(Note.class, id);
    }

    public Note getNoteAll(int id) {
        Note note = (Note) sessionFactory.getCurrentSession().get(Note.class,id);
        Hibernate.initialize(note.getLogin());
        return note;
    }

    public List<Note> getUserNotes(User login) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Note.class);
        criteria.add(Restrictions.like("login", login)).addOrder(Order.desc("dateCreate"));
        return criteria.list();
    }

    public List<Note> getLastUserNotes(User login) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Note.class);
        criteria.add(Restrictions.like("login", login)).addOrder(Order.desc("dateCreate")).setMaxResults(10);
        return criteria.list();
    }

    public List<Note> getAllNotes() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Note.class);
        criteria.addOrder(Order.desc("dateCreate"));
        return criteria.list();
    }
}