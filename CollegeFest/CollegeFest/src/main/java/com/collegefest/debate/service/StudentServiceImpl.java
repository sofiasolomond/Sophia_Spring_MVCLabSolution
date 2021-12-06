package com.collegefest.debate.service;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.collegefest.debate.entity.Student;

@Repository
public class StudentServiceImpl implements StudentService {

	private SessionFactory sessionFactory;
	private Session session;

	@Autowired
	StudentServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// This method lists all the participant details
	public List<Student> findAll() {
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
		Transaction transaction = session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Student> students = session.createQuery("from Student").list();
		transaction.commit();
		session.close();
		return students;
	}

	// This method saves participant information
	public void save(Student student) {
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
		Transaction transaction = session.beginTransaction();
		System.out.println("save");

		System.out.println(transaction);

		session.saveOrUpdate(student);
		transaction.commit();
		session.close();

	}

	// This method deletes particular participant details
	public void deleteById(int id) {
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
		Transaction transaction = session.beginTransaction();
		Student student = session.get(Student.class, id);
		session.delete(student);
		transaction.commit();
		session.close();

	}

	// This method finds a particular participant information
	public Student findById(int id) {
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
		Student student = session.get(Student.class, id);
		session.delete(student);
		session.close();
		return student;
	}
}
