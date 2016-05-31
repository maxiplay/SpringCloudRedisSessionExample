package com.springcloud.sessionexample.userdatamodel.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.springcloud.sessionexample.userdatamodel.model.User;

import net.bull.javamelody.MonitoredWithSpring;

/**
 * {@link User} repository
 * 
 * @author a525125
 *
 */
@MonitoredWithSpring(name = "UserRepository")
public interface UserRepository extends Repository<User, Long> {

	Page<User> findAll(Pageable pageable);

	User save(User user);

	User findOneById(String id);

	Optional<User> findOneByLogin(String login);

}