package com.springcloud.sessionexample.book.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.sessionexample.book.model.Book;
import com.springcloud.sessionexample.book.service.BookService;

import io.swagger.annotations.ApiOperation;

/**
 * Book controller
 * 
 * @author A525125
 *
 */
@RestController
@RequestMapping(value = "/book", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/{login}", method = RequestMethod.GET)
	@ApiOperation(value = "Get book list by user", response = Book.class, responseContainer = "List")
	public ResponseEntity<Page<Book>> getUserBooks(
			@PathVariable("login") String login, Pageable p) throws Exception {
		Page<Book> books = bookService.getUserBooks(login, p);
		HttpStatus status = books != null && books.getNumberOfElements() > 0 ? HttpStatus.OK
				: HttpStatus.NO_CONTENT;
		return ResponseEntity.status(status).body(books);
	}

	@RequestMapping(value = "/{bookId}", method = RequestMethod.PUT)
	@ApiOperation(value = "Update book", response = Book.class)
	public ResponseEntity<Book> updateBookTitle(
			@PathVariable("bookId") String bookId,
			@RequestBody String newBookTitle) throws Exception {
		return ResponseEntity.ok().body(
				bookService.updateBookTitle(bookId, newBookTitle));
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ApiOperation(value = "Create book", response = Book.class, responseContainer = "List")
	public ResponseEntity<Page<Book>> createNewBook(Principal principal,
			Pageable p) throws Exception {
		return ResponseEntity.ok()
				.body(bookService.createNewBook(principal, p));
	}

	@RequestMapping(value = "/{bookId}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete book", response = Book.class, responseContainer = "List")
	public ResponseEntity<Page<Book>> deleteBook(
			@PathVariable("bookId") String bookId, Principal principal,
			Pageable p) throws Exception {
		return ResponseEntity.ok().body(
				bookService.deleteBook(bookId, principal.getName(), p));
	}

	@RequestMapping(value = "/id/{bookId}", method = RequestMethod.GET)
	@ApiOperation(value = "Get book by Id", response = Book.class)
	public ResponseEntity<Book> getBook(@PathVariable("bookId") String bookId)
			throws Exception {
		return ResponseEntity.ok().body(bookService.getBook(bookId));
	}

}
