package com.bookapp.impl;

import com.bookapp.model.Book;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class BookDAOImpl {
//	BookDAOImpl dao=new BookDAOImpl();
//	Book book =new BookDAOImpl();

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("BookLibraryPU");

	///-> CREATE
	public void createBook(Book book) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx=null;
		try {
			em.getTransaction().begin();
			em.persist(book);
			em.getTransaction().commit();
			em.close();
		}catch(Exception e) {
			if(tx !=null && tx.isActive()) {
				tx.rollback();
			}
			throw e;  ///-> rethrow for controller/service layer
		}finally {
			em.close();
		}
	}

	///-> READ
	public Book findBook(int id) {
		EntityManager em = emf.createEntityManager();
		Book b = em.find(Book.class, id);
		em.close();

		return b;

	}
	
	///-> update
		public void updateBook(Book book) {
			EntityManager em=emf.createEntityManager();
			em.getTransaction().begin();
			em.merge(book);
			em.getTransaction().commit();
			em.close();
			
		
	}
		
		///-> DELELE
		public void deleteBook(int id) {
			EntityManager em=emf.createEntityManager();
			em.getTransaction().begin();
			Book book =em.find(Book.class, id);
			if(book!=null) {
				em.remove(book);
			}
			em.getTransaction().commit();
			em.clear();
		}
		

}
