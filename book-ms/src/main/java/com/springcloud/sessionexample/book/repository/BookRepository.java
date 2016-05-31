package com.springcloud.sessionexample.book.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.springcloud.sessionexample.book.model.Book;

import net.bull.javamelody.MonitoredWithSpring;

/**
 * {@link Book} repository
 * 
 * @author a525125
 *
 */
@MonitoredWithSpring(name = "BookRepository")
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

	Book findOneById(String id);

	@Override
	Book save(Book book);

	List<Book> findAllByOwner(String owner);

	Page<Book> findAllByOwner(String owner, Pageable p);

	Long removeById(String id);

}