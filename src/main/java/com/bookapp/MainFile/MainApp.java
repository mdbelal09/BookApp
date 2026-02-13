package com.bookapp.MainFile;

import java.time.LocalDate;

import com.bookapp.impl.BookDAOImpl;
import com.bookapp.model.Book;



public class MainApp {
	public static void main(String[] args) {
		
		BookDAOImpl dao= new BookDAOImpl();
		
		///-> save operation
		 
		Book b=new Book();
		b.setTitle("effective js ");
		b.setAuthor("mdbelal ");
		b.setDescription("a comprehensive guide to best practices.");
		b.setPublicationDate(LocalDate.of(2011, 4, 6));
		b.setIdentifier("ISBN-295");
		b.setPrice(490.80);
		b.setCategory("programing");
		b.setAvaliable(false);
		dao.createBook(b);
		System.out.println("book saved successfully! ID :"+b.getId());
		
		
		///-> READ & UPDATE operation
		Book existingBook =dao.findBook(b.getId());
		if(existingBook!=null) {
			existingBook.setPrice(58.99);///-> price drop
			dao.updateBook(existingBook);
			System.out.println("Book update! new price : "+existingBook.getPrice());
		}
		
		
		///-> DELETE 
		dao.deleteBook(2);
		System.out.println("bool detials are deleted!");
	}

}
