package com.springcloud.sessionexample.userdatamodel.repository;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.springcloud.sessionexample.userdatamodel.model.User;
import com.springcloud.sessionexample.userdatamodel.model.UserView;
import com.springcloud.sessionexample.userdatamodel.service.UserService;

/**
 * Auth controller
 * 
 * @author a525125
 *
 */
@RestController
public class AuthController {

	@Autowired
	private UserService userService;

	@JsonView(UserView.Protected.class)
	@ResponseBody
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(Principal user, HttpSession session) {
		System.out.println(user);
		return ResponseEntity.ok().body(userService.getMongoUser(user));
	}

}
