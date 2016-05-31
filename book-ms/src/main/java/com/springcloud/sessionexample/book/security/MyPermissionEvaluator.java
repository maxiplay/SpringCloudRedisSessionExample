package com.springcloud.sessionexample.book.security;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.springcloud.sessionexample.book.model.Book;
import com.springcloud.sessionexample.book.repository.BookRepository;

@Service
public class MyPermissionEvaluator implements PermissionEvaluator {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public boolean hasPermission(Authentication authentication, Object oTargetDomainObject, Object oPermission) {

		String login = authentication.getName();
		String permission = (String) oPermission;
		String targetDomainObject = (String) oTargetDomainObject;

		switch (permission) {

		case "hasSameLogin":

			if (authentication.getName().equals(oTargetDomainObject)) {
				return true;
			}

			break;

		case "bookOwner":

			Book book = bookRepository.findOneById(targetDomainObject);
			if (login.equals(book.getOwner())) {

				return true;
			}

			break;

		}

		return false;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {

		throw new RuntimeException("ID based permission evaluation currently not supported.");
	}
}