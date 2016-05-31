package com.worldline.springcloudarchetype.userdatamodel.service;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.worldline.springcloudarchetype.userdatamodel.model.User;
import com.worldline.springcloudarchetype.userdatamodel.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User getMongoUser(Principal user) {
		if (user != null) {
			String name = user.getName();
			Optional<User> mongoUser = userRepository.findOneByLogin(name);
			return mongoUser.orElse(null);
		}
		return null;
	}

}
