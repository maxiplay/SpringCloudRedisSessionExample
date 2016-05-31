package com.worldline.springcloudarchetype.common.service;

import org.springframework.security.access.annotation.Secured;

public interface ITestService {

	
	@Secured("ROLE_USER")
	public String testAuthent(String kikou);
}
