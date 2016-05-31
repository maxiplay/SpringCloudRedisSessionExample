package com.springcloud.sessionexample.book.service;

import java.security.Principal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.springcloud.sessionexample.book.model.Book;
import com.springcloud.sessionexample.book.repository.BookRepository;

import net.bull.javamelody.MonitoredWithSpring;

@MonitoredWithSpring
@Service
public class BookService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BookRepository bookRepository;

	@PreAuthorize("hasPermission(#login, 'hasSameLogin')")
	public Page<Book> getUserBooks(String login, Pageable p) {
		return bookRepository.findAllByOwner(login, p);
	}

	@PreAuthorize("hasPermission(#bookId, 'bookOwner')")
	public Page<Book> deleteBook(String bookId, String login, Pageable p) throws Exception {
		bookRepository.removeById(bookId);
		return bookRepository.findAllByOwner(login, p);
	}

	public Page<Book> createNewBook(Principal principal, Pageable p) {
		String owner = principal.getName();
		try {
			Book book = new Book();
			book.setTitle("New Book");
			book.setResume("Resume of new book");
			book.setOwner(owner);
			book.setCreationDate(new Date());
			bookRepository.save(book);

			return bookRepository.findAllByOwner(owner, p);

		} catch (RuntimeException e) {
			log.error("Erreur durant le traitement de creation de book", e);
		}

		return null;

	}

	@PreAuthorize("hasPermission(#bookId, 'bookOwner')")
	public Book updateBookTitle(String bookId, String newBookTitle) {
		Book book = bookRepository.findOneById(bookId);
		book.setTitle(newBookTitle);
		return bookRepository.save(book);

	}

	@PreAuthorize("hasPermission(#bookId, 'bookOwner')")
	public Book getBook(String bookId) {
		return bookRepository.findOneById(bookId);
	}

}
