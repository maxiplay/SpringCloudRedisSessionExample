package com.worldline.springcloudarchetype.book.repository;

import java.util.List;

import net.bull.javamelody.MonitoredWithSpring;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.worldline.springcloudarchetype.book.model.Book;

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