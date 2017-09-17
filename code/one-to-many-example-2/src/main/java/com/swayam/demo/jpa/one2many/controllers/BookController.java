package com.swayam.demo.jpa.one2many.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.swayam.demo.jpa.one2many.model.Book;
import com.swayam.demo.jpa.one2many.service.BookService;

@RestController
public class BookController {

	private final BookService bookService;

	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@RequestMapping(path = "/books/{bookId}", method = RequestMethod.GET)
	public Book getBook(@PathVariable("bookId") Long bookId) {
		return bookService.getBook(bookId);
	}

}
