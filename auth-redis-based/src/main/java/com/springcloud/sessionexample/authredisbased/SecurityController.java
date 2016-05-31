package com.springcloud.sessionexample.authredisbased;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Gateway controller
 * 
 * @author a525125
 *
 */
@Controller
public class SecurityController {

	@ResponseBody
	@RequestMapping(value = "/clogout", method = RequestMethod.POST)
	public ResponseEntity<String> logout(HttpSession session) {
		session.invalidate();
		return ResponseEntity.ok().body("ok");
	}

}